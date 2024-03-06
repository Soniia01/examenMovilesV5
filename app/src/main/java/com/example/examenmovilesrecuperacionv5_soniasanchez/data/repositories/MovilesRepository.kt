package com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.remote.MovilesRemoteDataSource
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovilesRepository @Inject constructor(
    private val movilesRemoteDataSource: MovilesRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

     fun getMoviles(): Flow<NetworkResult<List<Movil>>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = movilesRemoteDataSource.getMoviles()
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    fun updateMovil(movil: Movil): Flow<NetworkResult<Movil>> {
        return flow {
            emit(NetworkResult.Loading())
            val result = movilesRemoteDataSource.updateMovil(movil)
            emit(result)
        }.flowOn(dispatcher)
    }
}