package com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.remote.EmpleadosRemoteDataSource
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens.LoginTokens
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Empleado
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EmpleadosRepository @Inject constructor(
    private val usuariosRemoteDataSource: EmpleadosRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getEmpleados(): Flow<NetworkResult<List<Empleado>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = usuariosRemoteDataSource.getEmpleados()
            emit(result)
        }.flowOn(dispatcher)
    }
    fun doLogin(name:String, password:String): Flow<NetworkResult<LoginTokens>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = usuariosRemoteDataSource.getLogin(name,password)
            emit(result)
        }.flowOn(dispatcher)
    }
}