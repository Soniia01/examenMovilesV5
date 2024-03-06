package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.moviles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examenmovilesrecuperacionv5_soniasanchez.R
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases.GetMovilesUseCase
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases.UpdateMovilUseCase
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
class MovilesViewModel @Inject constructor(
    private val stringProvider: StringProvider,
    private val movilUseCase: UpdateMovilUseCase,
    private val movilUseCase2: GetMovilesUseCase,
    ) : ViewModel() {

    private val _uiState: MutableStateFlow<MovilesState> =
        MutableStateFlow(MovilesState())
    val state : StateFlow<MovilesState> = _uiState.asStateFlow()

    fun event(event: MovilesEvents) {
        when (event) {
            is MovilesEvents.UpdateMovil -> updateMovil(event.movil)
            is MovilesEvents.CambiarId -> cambiarId(event.id)
            is MovilesEvents.CambiarModelo -> cambiarModelo(event.modelo)
            is MovilesEvents.CambiarAnio -> cambiarAnio(event.anio)
            is MovilesEvents.CambiarCapacidad -> cambiarCapacidad(event.capacidad)
            is MovilesEvents.CambiarEmpleado -> cambiarEmpleado(event.empleado)
            is MovilesEvents.GetMoviles-> getMoviles()
        }
    }

    private fun cambiarId(id:Int){
        _uiState.update {
            it.copy(movil = it.movil?.copy(id = id))
        }
    }

    private fun cambiarModelo(modelo:String){
        _uiState.update {
            it.copy(movil = it.movil?.copy(modelo = modelo))
        }
    }

    private fun cambiarAnio(anio:Int){
        _uiState.update {
            it.copy(movil = it.movil?.copy(year = anio))
        }
    }

    private fun cambiarCapacidad(capacidad:Int){
        _uiState.update {
            it.copy(movil = it.movil?.copy(capacidad = capacidad))
        }
    }

    private fun cambiarEmpleado(empleado:Int){
        _uiState.update {
            it.copy(movil = it.movil?.copy(empleado = empleado))
        }
    }

    private fun updateMovil(movil: Movil?) {
        viewModelScope.launch {
            movil?.let {
                movilUseCase.invoke(it)
                    .collect{result->

                        when(result){
                            is NetworkResult.Error -> {
                                _uiState.update {
                                    if (result.message.equals("NOT_FOUND")) {
                                        it.copy(message = "NOT_FOUND")
                                    } else if(result.message.equals("BAD_REQUEST")) {
                                        it.copy(message = "BAD_REQUEST")
                                    } else {
                                        it.copy(message = result.message, failedToken = true)
                                    }
                                }
                            }
                            is NetworkResult.Loading -> _uiState.update { it.copy(isLoading = true) }
                            is NetworkResult.Success -> _uiState.update {
                                it.copy(message = stringProvider.getString(R.string.updateMovil),
                                    movil = result.data , isLoading = false
                                )
                            }
                        }
                    }
            }
        }
    }
    private fun getMoviles() {
        viewModelScope.launch {
            movilUseCase2.invoke()
                .collect { result ->
                    when (result) {
                        is NetworkResult.Error -> {
                            _uiState.update {
                                it.copy(isLoading = false)
                            }
                        }
                        is NetworkResult.Loading -> _uiState.update {
                            it.copy(isLoading = true)
                        }
                        is NetworkResult.Success -> _uiState.update {
                            it.copy(moviles = result.data ?: emptyList(), isLoading = false)
                        }
                    }
                }
        }
    }
}

