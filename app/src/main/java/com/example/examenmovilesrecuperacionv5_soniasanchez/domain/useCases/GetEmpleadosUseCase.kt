package com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases


import com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories.EmpleadosRepository
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Empleado
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmpleadosUseCase @Inject constructor(private val usuariosRepository: EmpleadosRepository) {
    operator fun invoke(): Flow<NetworkResult<List<Empleado>>> {
        return usuariosRepository.getEmpleados()
    }
}
