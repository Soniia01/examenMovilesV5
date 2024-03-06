package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.equipos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenmovilesrecuperacionv5_soniasanchez.R
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Equipo
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases.GetEquipoByIdUseCase
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases.GetEquiposUseCase
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.StringProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquiposViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val getEquiposUseCase: GetEquiposUseCase,
    private val getEquipoByIdUseCase: GetEquipoByIdUseCase
    ) : ViewModel() {
    private val _uistate: MutableStateFlow<EquiposState> by lazy {
        MutableStateFlow(EquiposState())
    }
    val state : StateFlow<EquiposState> = _uistate.asStateFlow()

    fun event(event: EquiposEvent) {
        when (event) {
            EquiposEvent.getEquipos -> cargarLista()
            is EquiposEvent.GetEquipoById -> getEquipoById(event.id)
            is EquiposEvent.CambiarId -> cambiarId(event.id)
        }
    }

    fun cargarLista() {
        viewModelScope.launch {

            getEquiposUseCase.invoke()
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uistate.update {
                                it.copy(message = result.message)
                            }
                        }

                        is NetworkResult.Loading -> _uistate.update { it.copy(isLoading = true) }
                        is NetworkResult.Success -> _uistate.update {
                            it.copy(equipos = ((result.data ?: emptyList()) as List<Equipo>), isLoading = false)
                        }
                    }
                }
        }
    }

    private fun cambiarId(id:String){
        _uistate.update {
            it.copy(id = id)
        }
    }

    private fun getEquipoById(id: String?) {
        viewModelScope.launch {
            id?.let {
                getEquipoByIdUseCase.invoke(id.toInt())
                    .collect{result->

                        when(result){
                            is NetworkResult.Error -> _uistate.update {
                                it.copy(message = stringProvider.getString(R.string.getEquipoNoValido))
                            }
                            is NetworkResult.Loading -> _uistate.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uistate.update {
                                it.copy(message = stringProvider.getString(R.string.getEquipoValido), isLoading = false)
                            }
                        }
                    }
            }
        }
    }
}

