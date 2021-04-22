package com.example.mobillabor.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobillabor.model.Player
import com.example.mobillabor.model.Team


//TODO: Entities
@Database(entities = [Player::class, Team::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun footballDao(): FootballDAO

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "football_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }

    }
}