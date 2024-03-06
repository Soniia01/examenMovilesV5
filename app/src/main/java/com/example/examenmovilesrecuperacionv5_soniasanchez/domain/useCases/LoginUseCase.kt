package com.example.examenmovilesrecuperacionv5_soniasanchez.domain.useCases


import com.example.examenmovilesrecuperacionv5_soniasanchez.data.repositories.EmpleadosRepository
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens.LoginTokens
import com.example.examenmovilesrecuperacionv5_soniasanchez.domain.model.Empleado
import com.example.examenmovilesrecuperacionv5_soniasanchez.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val empleadosRepository: EmpleadosRepository,
) {
    operator fun invoke(nombre :String, password: String): Flow<NetworkResult<LoginTokens>> {
          return empleadosRepository.doLogin(nombre,password)
    }
}