package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.moviles

import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil

open class MovilesEvents {
    object GetMoviles : MovilesEvents()
    class UpdateMovil(val movil: Movil?): MovilesEvents()
    class CambiarId(val id:Int): MovilesEvents()
    class CambiarModelo(val modelo:String): MovilesEvents()
    class CambiarAnio(val anio:Int): MovilesEvents()
    class CambiarCapacidad(val capacidad:Int): MovilesEvents()
    class CambiarEmpleado(val empleado:Int): MovilesEvents()
}