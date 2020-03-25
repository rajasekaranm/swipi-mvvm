package com.example.swapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.swapi.data.database.dao.PeopleDao
import com.example.swapi.data.model.PeopleRemote

@Database(
    entities = [
        PeopleRemote::class
    ],
    version = 1,
    exportSchema = true
)
abstract class SwapiDatabase: RoomDatabase() {
    abstract val peopleDao: PeopleDao
}