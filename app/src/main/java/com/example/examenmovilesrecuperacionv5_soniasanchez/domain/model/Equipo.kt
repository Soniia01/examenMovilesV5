package com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model


data class Equipo(
 val id: Int,
 val nombre: String,
 val jugadores: List<Jugador>)