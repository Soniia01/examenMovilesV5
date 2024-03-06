package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.equipos

open class EquiposEvent {
    object getEquipos: EquiposEvent()
    class GetEquipoById(val id: String): EquiposEvent()
    class CambiarId(val id:String): EquiposEvent()
}