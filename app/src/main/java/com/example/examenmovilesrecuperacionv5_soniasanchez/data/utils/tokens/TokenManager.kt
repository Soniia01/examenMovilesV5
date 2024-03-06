package com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.tokens

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.utils.NetworkModule.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private val accessToken = stringPreferencesKey("accessToken")
    }

    suspend fun deleteAccessToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(accessToken)
        }
    }

    fun getAccessToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[accessToken]
        }
    }

    suspend fun saveAccessToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[accessToken] = token
        }
    }
}