package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.nav

val screensBottomBar = listOf(
    Screens("Login"),
    Screens("Equipos"),
    Screens("Empleados"),
    Screens("Moviles")
)

data class Screens(val route: String)
