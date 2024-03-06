package com.example.examenmovilesrecuperacionv5_soniasanchez.data.remote

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.retrofit.EmpleadosApi
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens.LoginTokens
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens.TokenManager
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Empleado
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult

import javax.inject.Inject


class EmpleadosRemoteDataSource @Inject constructor(
    private val empleadosApi: EmpleadosApi,
    private val tokenManager: TokenManager
) {
    suspend fun getLogin(mail: String, password: String): NetworkResult<LoginTokens> {
        return try {
            tokenManager.deleteAccessToken()
            val response = empleadosApi.login(mail, password)
            if (response.isSuccessful) {
                val loginTokens = response.body()
                loginTokens?.let {
                    val accessToken = loginTokens.accessToken
                    if (accessToken != null) {
                        tokenManager.saveAccessToken(accessToken)
                        NetworkResult.Success(loginTokens)
                    } else {
                        NetworkResult.Error("AccessToken is null")
                    }
                } ?: NetworkResult.Error("Body is null")
            } else {
                if (response.code() == 403) {
                    NetworkResult.Error("Incorrect username or password")
                } else {
                    NetworkResult.Error("${response.code()} ${response.message()}")
                }
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
    suspend fun getEmpleados(): NetworkResult<List<Empleado>> {
        return try {
            val response = empleadosApi.getEmpleados()
            if (response.isSuccessful) {
                val empleados = response.body()
                empleados?.let { return NetworkResult.Success(it) } ?: error("No data")
            } else {
                if (response.code() == 403) {
                    Error("Unauthorized")
                }
                NetworkResult.Error(response.message())
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: e.toString())
        }
    }
}

