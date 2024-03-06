package com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.remote.EquiposRemoteDataSource
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Equipo
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EquiposRepository @Inject constructor(
    private val equiposRemoteDataSource: EquiposRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun getEquipos(): Flow<NetworkResult<List<Equipo>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = equiposRemoteDataSource.getEquipos()
            emit(result as NetworkResult<List<Equipo>>)
        }.flowOn(dispatcher)
    }

    fun getEquipoById(id: Int): Flow<NetworkResult<Equipo>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = equiposRemoteDataSource.getEquipoById(id)
            emit(result as NetworkResult<Equipo>)
        }.flowOn(dispatcher)
    }
}