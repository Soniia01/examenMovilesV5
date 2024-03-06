package com.example.examenmovilesrecuperacionv5_soniasanchez.data.remote


import android.util.Log
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.retrofit.MovilesApi
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens.TokenManager
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import javax.inject.Inject

class MovilesRemoteDataSource @Inject constructor(
    private val movilesApi: MovilesApi
) {
    suspend fun getMoviles(): NetworkResult<List<Movil>> {
        return try {
            val response = movilesApi.getAllMoviles()
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("Moviles (RemoteDataSource)", "Moviles: ${response}")
                    return NetworkResult.Success(response.body().orEmpty())
                }
                return error("No hay datos")
            }
            return error("Ha habido un error al conseguir la información")

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }

    suspend fun updateMovil(movil: Movil): NetworkResult<Movil> {
        return try {
            val response = movilesApi.updateMovil(movil)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("Moviles (RemoteDataSource)", "Moviles: ${response}")
                    if (response.body()!=null) {
                        return NetworkResult.Success(response.body()!!)
                    }
                }
                return error("No hay datos")
            }
            return error("Ha habido un error al conseguir la información")

        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: e.toString())
        }
    }
}

