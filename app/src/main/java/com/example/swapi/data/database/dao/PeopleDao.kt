package com.example.swapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.swapi.data.model.PeopleRemote

@Dao
interface PeopleDao {

    @Query("SELECT * FROM people")
    fun selectAll(): List<PeopleRemote>

    @Query("SELECT COUNT(*) from people")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg data: PeopleRemote)
}