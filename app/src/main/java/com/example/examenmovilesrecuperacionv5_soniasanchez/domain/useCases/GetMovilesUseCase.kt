package com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories.MovilesRepository
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Movil
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovilesUseCase @Inject constructor(private val movilesRepository: MovilesRepository) {
    operator fun invoke(): Flow<NetworkResult<List<Movil>>> {
          return movilesRepository.getMoviles()
    }
}