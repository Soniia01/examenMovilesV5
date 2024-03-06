package com.example.examenmovilesrecuperacionv5_soniasanchez.data.remote

import com.apollographql.apollo3.ApolloClient
import com.apolloproject.GetEquipoByIdQuery
import com.apolloproject.GetEquiposQuery
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.graphql.toEquipo
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Equipo
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import java.lang.Exception
import javax.inject.Inject


class EquiposRemoteDataSource@Inject constructor(
    private val apolloClient: ApolloClient
) {

    suspend fun getEquipos(): NetworkResult<List<Equipo>> {
        return try {
            val response = apolloClient.query(GetEquiposQuery()).execute()
            if (response.hasErrors()) {
                NetworkResult.Error(
                    response.errors?.first()?.message ?: "Error desconocido"
                )
            } else {
                val equipos = response.data?.getEquipos?.mapNotNull { it?.toEquipo() } ?: emptyList()
                if (equipos.isNotEmpty()) {
                    NetworkResult.Success(equipos)
                } else {
                    NetworkResult.Error("Error desconocido")
                }
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun getEquipoById(id: Int): NetworkResult<Equipo> {
        return try {
            val response = apolloClient.query(GetEquipoByIdQuery(id)).execute()
            if (response.hasErrors()) {
                NetworkResult.Error(
                    response.errors?.first()?.message ?: "Error desconocido"
                )
            } else {
                val equipo = response.data?.getEquipo?.toEquipo()
                if (equipo != null) {
                    NetworkResult.Success(equipo)
                } else {
                    NetworkResult.Error("Error desconocido")
                }
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

}

