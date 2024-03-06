package com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ServiceInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getAccessToken().first()
        }
        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrBlank()) { // Check for non-empty token
                header("Authorization", "Bearer $token")
            }
        }.build()
        return chain.proceed(request)
    }
}