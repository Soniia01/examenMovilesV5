package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases.GetEmpleadosUseCase
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases.GetMovilesUseCase
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmpleadosViewModel @Inject constructor(
    private val getEmpleadosUseCase: GetEmpleadosUseCase,
    private val getMovilesUseCase: GetMovilesUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<EmpleadosState> =
        MutableStateFlow(EmpleadosState())
    val state : StateFlow<EmpleadosState> = _uiState.asStateFlow()

    fun event(event: EmpleadosEvent) {
        when (event) {
            EmpleadosEvent.getEmpleados -> cargarLista(ListaType.EMPLEADOS)
            EmpleadosEvent.getMoviles -> cargarLista(ListaType.MOVILES)
        }
    }

    private fun cargarLista(listaType: ListaType) {
        viewModelScope.launch {
            when (listaType) {
                ListaType.EMPLEADOS -> {
                    getEmpleadosUseCase.invoke()
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Error -> _uiState.update {
                                    it.copy(
                                        listaType = ListaType.MOVILES,
                                        empleados = emptyList(),
                                        isLoading = false
                                    )
                                }
                                is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                                is NetworkResult.Success -> _uiState.update {
                                    it.copy(empleados = (result.data ?: emptyList()), isLoading = false)
                                }
                            }
                        }
                }
                ListaType.MOVILES -> {
                    getMovilesUseCase.invoke()
                        .collect { result ->
                            when (result) {
                                is NetworkResult.Error -> {
                                    _uiState.update {
                                        it.copy(
                                            listaType = ListaType.EMPLEADOS,
                                            message = result.message,
                                            failedToken = false,
                                            moviles = emptyList(),
                                            isLoading = false
                                        )
                                    }
                                }
                                is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                                is NetworkResult.Success -> _uiState.update {
                                    it.copy(
                                        moviles = (result.data ?: emptyList()),
                                        isLoading = false
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}

