package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.login

data class LoginState(
    val username: String ,
    val password: String ,
    val error: String? = null,
    val loading: Boolean = false,
    val login: Boolean = false
)