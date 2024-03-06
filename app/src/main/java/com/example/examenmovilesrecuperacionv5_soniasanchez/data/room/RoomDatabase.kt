package com.example.examenmovilesrecuperacionv5_soniasanchez.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.dao.EmpleadoDAO
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.EmpleadoEntity
import com.example.examenmovilesrecuperacionv5_soniasanchez.data.room.model.MovilEntity

@Database(entities = [EmpleadoEntity::class, MovilEntity::class], version = 5, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun empleadoDao(): EmpleadoDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "EmpleadosMovilesDatabase"
                )
                    .fallbackToDestructiveMigrationFrom(1)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
