package com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases

import com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories.EquiposRepository
import javax.inject.Inject

class GetEquipoByIdUseCase @Inject constructor(private val equiposRepository: EquiposRepository) {
     operator fun invoke(id: Int) = equiposRepository.getEquipoById(id)
}