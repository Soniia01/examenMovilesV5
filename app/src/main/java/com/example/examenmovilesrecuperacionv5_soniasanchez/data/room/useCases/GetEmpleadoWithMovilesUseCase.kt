package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.useCases

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.dao.EmpleadoDAO
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.EmpleadoWithMoviles

class GetEmpleadoWithMovilesUseCase(private val empleadoDao: EmpleadoDAO) {
    suspend operator fun invoke(empleadoId: Int): EmpleadoWithMoviles? {
        return empleadoDao.getEmpleadoWithMoviles(empleadoId)

    }
}