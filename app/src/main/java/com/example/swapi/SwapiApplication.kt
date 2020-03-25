package com.example.swapi

import android.app.Application
import com.example.swapi.data.database.DatabaseManager

class SwapiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDatabase()
    }

    // Init the database access
    private fun initDatabase() {
        DatabaseManager.getInstance(this)
    }
}