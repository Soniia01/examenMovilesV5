package com.example.examenmovilesrecuperacionv5_soniasanchez.data.retrofit

import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface MovilesApi {

    @GET("/moviles")
    suspend fun getAllMoviles(): Response<List<Movil>>

    @PUT("/movil")
    suspend fun updateMovil(@Body movil:Movil): Response<Movil>
}
