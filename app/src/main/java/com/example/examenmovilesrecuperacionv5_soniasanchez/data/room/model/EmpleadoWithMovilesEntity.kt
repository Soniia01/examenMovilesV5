package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model

import androidx.room.Embedded
import androidx.room.Relation

data class EmpleadoWithMoviles(
    @Embedded val empleado: EmpleadoEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "empleadoId"
    )
    val moviles: List<MovilEntity>
)