package com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model

import java.time.LocalDate

data class Empleado(val username: String= "",
                    val password: String = "",
                    val doB: LocalDate? = null,
                    val moviles: List<Movil>? = null,
                    val rol:String="")
