package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.LocalDateConverter
import java.time.LocalDate

@Entity(tableName = "empleados")
@TypeConverters(LocalDateConverter::class)
class EmpleadoEntity (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "fechaNacimiento")
    val doB: LocalDate,
    @ColumnInfo(name = "nombre")
    val nombre:String
)