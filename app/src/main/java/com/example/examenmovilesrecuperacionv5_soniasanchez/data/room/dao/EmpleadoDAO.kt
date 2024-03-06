package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.EmpleadoEntity
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.EmpleadoWithMoviles
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.MovilEntity

@Dao
interface EmpleadoDAO {
    @Query("SELECT * FROM empleados")
    suspend fun getAllEmpleados(): List<EmpleadoEntity>

    @Query("SELECT * FROM empleados WHERE id = :empleadoId")
    suspend fun getEmpleado(empleadoId: Int): EmpleadoEntity?

    @Transaction
    @Query("SELECT * FROM empleados WHERE id = :empleadoId")
    suspend fun getEmpleadoWithMoviles(empleadoId: Int): EmpleadoWithMoviles?

    @Update
    suspend fun updateMovil(movil: MovilEntity)
}