package com.example.examenmovilesrecuperacionv5_soniasanchez.data.graphql

import com.apolloproject.GetEquipoByIdQuery
import com.apolloproject.GetEquiposQuery
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Equipo
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Jugador

fun GetEquiposQuery.GetEquipo.toEquipo(): Equipo {
    return Equipo(
        id = id,
        nombre = nombre.toString(),
        jugadores = this.jugadores?.map { it?.toJugador() } as List<Jugador>? ?: emptyList()
    )
}

fun GetEquiposQuery.Jugadore.toJugador(): Jugador {
    return Jugador(
        id = id,
        nombre = nombre.toString(),
    )
}

fun GetEquipoByIdQuery.GetEquipo.toEquipo(): Equipo {
    return Equipo(
        id = 0,
        nombre = nombre.toString(),
        jugadores = this.jugadores?.map { it?.toJugador() } as List<Jugador>? ?: emptyList()
    )
}

fun GetEquipoByIdQuery.Jugadore.toJugador(): Jugador {
    return Jugador(
        id = id,
        nombre = nombre.toString(),
    )
}
