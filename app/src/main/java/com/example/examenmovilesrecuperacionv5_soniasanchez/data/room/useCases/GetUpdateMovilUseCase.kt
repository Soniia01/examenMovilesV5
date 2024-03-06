package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.useCases

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.dao.EmpleadoDAO
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.EmpleadoEntity
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.MovilEntity
import javax.inject.Inject

class GetUpdateMovilUseCase @Inject constructor(private val empleadoDao: EmpleadoDAO) {
    suspend operator fun invoke(movilEntity: MovilEntity) {
        return empleadoDao.updateMovil(movilEntity)
    }
}