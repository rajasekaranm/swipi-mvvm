package com.example.swapi.data.database

import androidx.room.Room
import com.example.swapi.SwapiApplication


private class DatabaseManagerImpl(
    override val database: SwapiDatabase
) : DatabaseManager

interface DatabaseManager {

    val database: SwapiDatabase

    companion object {
        private const val DATABASE_NAME = "swapi.db"

        @Volatile
        private var databaseManager: DatabaseManager? = null

        var override: DatabaseManager? = null

        fun getInstance(app: SwapiApplication? = null): DatabaseManager {
            return override ?: databaseManager ?: synchronized(this) {
                DatabaseManagerImpl(
                    Room.databaseBuilder(
                        app ?: throw IllegalStateException("No Application"),
                        SwapiDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                ).also {
                    databaseManager = it
                }
            }
        }

    }
}