package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviles")
class MovilEntity (
    @PrimaryKey
    val movilID: Int,
    @ColumnInfo(name = "modelo")
    val modelo: String,
    @ColumnInfo(name = "anyo")
    val anyo: Int,
    @ColumnInfo(name = "capacidad")
    val capacidad: Int,
    @ColumnInfo(name = "empleadoId")
    val empleadoId: Int
)
