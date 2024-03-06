package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.useCases

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.dao.EmpleadoDAO
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.EmpleadoEntity
import javax.inject.Inject

class GetEmpleadosUseCase @Inject constructor(private val empleadoDao: EmpleadoDAO) {
    suspend operator fun invoke(): List<EmpleadoEntity> {
        return empleadoDao.getAllEmpleados()
    }
}