package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.moviles

import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil

data class MovilesState(
    val message:String?=null,
    val isLoading:Boolean=false,
    val failedToken: Boolean = false,
    val movil: Movil? = Movil(id = 0, modelo = "", year = 0, capacidad = 0, empleado = 0),
    val moviles: List<Movil> = emptyList(),

    )