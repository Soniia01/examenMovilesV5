package com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories.EquiposRepository
import javax.inject.Inject

class GetEquiposUseCase @Inject constructor(private val equiposRepository: EquiposRepository) {
     operator fun invoke() = equiposRepository.getEquipos()
}