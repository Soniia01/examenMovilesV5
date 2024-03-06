package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.useCases

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.dao.EmpleadoDAO
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.EmpleadoEntity
import javax.inject.Inject

class GetEmpleadoByIdUseCase @Inject constructor(private val empleadoDao: EmpleadoDAO) {
    suspend operator fun invoke(empleadoId: Int): EmpleadoEntity? {
        return empleadoDao.getEmpleado(empleadoId)
    }
}