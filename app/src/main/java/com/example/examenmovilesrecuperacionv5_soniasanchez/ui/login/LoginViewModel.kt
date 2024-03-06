package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases.LoginUseCase
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<LoginState> by lazy {
        MutableStateFlow(LoginState("", ""))
    }

    val uiState: StateFlow<LoginState> = _uiState

    init {
        _uiState.value = LoginState(
            error = null,
            username = "",
            password = ""
        )
    }

    fun event(event: LoginEvents) {
        when (event) {
            is LoginEvents.setUserState -> _uiState.update {
                it.copy(
                    username = event.username
                )
            }

            is LoginEvents.setPasswordState -> _uiState.update {
                it.copy(
                    password = event.password
                )
            }

            is LoginEvents.login -> {
                login()
            }

            LoginEvents.Error -> _uiState.update { it.copy(error = null) }
        }
    }

    private fun login() {
        if (uiState.value.username.isNullOrBlank() || uiState.value.password.isNullOrBlank()) {
            _uiState.update { it.copy(error = "Usuario o password vacios") }
        } else {
            viewModelScope.launch {
                loginUseCase(_uiState.value.username, _uiState.value.password)
                    .catch(action = { cause ->
                        _uiState.update {
                            it.copy(
                                error = cause.message,
                                loading = false
                            )
                        }
                    })
                    .collect { result ->
                        when (result) {
                            is NetworkResult.Error<*> -> {
                                _uiState.update {
                                    it.copy(
                                        error = result.message,
                                        loading = false
                                    )
                                }
                            }
                            is NetworkResult.Loading<*> -> _uiState.update { it.copy(loading = true) }
                            is NetworkResult.Success<*> -> _uiState.update {
                                it.copy(
                                    login = true,
                                    loading = false
                                )
                            }
                        }
                    }
            }
        }
    }
}
