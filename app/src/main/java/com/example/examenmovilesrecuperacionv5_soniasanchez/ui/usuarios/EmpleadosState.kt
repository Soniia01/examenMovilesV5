package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.usuarios

import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Empleado
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil

data class EmpleadosState (
    val empleados: List<Empleado> = emptyList(),
    val moviles:List<Movil> = emptyList(),
    val listaType: ListaType = ListaType.EMPLEADOS,
    val message:String?=null,
    val failedToken: Boolean = false,
    val isLoading:Boolean=false
)