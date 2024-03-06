package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room

import android.content.Context
import androidx.room.Room
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.dao.EmpleadoDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideEmpleadoDAO(roomDatabase: AppDatabase): EmpleadoDAO {
        return roomDatabase.empleadoDao()
    }
}