package com.example.examenmovilesrecuperacionv5_soniasanchez.ui.login

open class LoginEvents {
    class login: LoginEvents()
    class setUserState(val username: String):LoginEvents()
    class setPasswordState(val password: String):LoginEvents()
    object Error : LoginEvents()
}