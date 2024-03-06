package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.equipos

import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Equipo


data class EquiposState(
    val equipos:List<Equipo> = emptyList(),
    val id:String?=null,
    val message:String?=null,
    val isLoading:Boolean=false,)