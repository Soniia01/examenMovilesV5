package com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.examenmovilesrecuperacionv5_soniasanchez.common.URLS
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.retrofit.EmpleadosApi
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.retrofit.MovilesApi
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens.ServiceInterceptor
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(
        authInterceptor: ServiceInterceptor,
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterMoshiFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .add(LocalDateAdapter())
            .add(LocalDateTimeAdapter())
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URLS.BASEAUTH_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun createApolloClient(): ApolloClient {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return ApolloClient.Builder()
            .serverUrl(URLS.BASEGRAPHQL_URL)
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
    }

    @Singleton
    @Provides
    fun getApiMoviles(retrofit: Retrofit): MovilesApi =
        retrofit.create(MovilesApi::class.java)

    @Singleton
    @Provides
    fun getApiEmpleados(retrofit: Retrofit): EmpleadosApi =
        retrofit.create(EmpleadosApi::class.java)

    @Singleton
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}


class LocalDateAdapter {

    @ToJson
    fun toJson(value: LocalDate): String {
        return value.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    @FromJson
    fun fromJson(value: String): LocalDate {
        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE)
    }
}

class LocalDateTimeAdapter {

    @ToJson
    fun toJson(value: LocalDateTime): String {
        return value.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }

    @FromJson
    fun fromJson(value: String): LocalDateTime {
        return LocalDateTime.parse(value, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    }
}