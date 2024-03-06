package com.example.examenmovilesrecuperacionv5_soniasanchez.data.retrofit

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens.LoginTokens
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Empleado
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EmpleadosApi {

    @GET("/login")
    suspend fun login(@Query("nombre") nombre:String, @Query("password") password:String ): Response<LoginTokens>

    @GET("/empleados")
    suspend fun getEmpleados():Response<List<Empleado>>

}
