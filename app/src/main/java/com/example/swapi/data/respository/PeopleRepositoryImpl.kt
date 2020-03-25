package com.example.swapi.data.respository

import com.example.swapi.data.database.DatabaseManager
import com.example.swapi.data.database.SwapiDatabase
import com.example.swapi.data.model.PeopleRemote
import com.example.swapi.data.networking.HttpClientManager
import com.example.swapi.data.networking.api.PeopleAPI
import com.example.swapi.data.networking.createApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeopleRepositoryImpl(private val api: PeopleAPI,private val database: SwapiDatabase) : PeopleRepository{

    override suspend fun getPeople(id: Int):PeopleRemote? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPeople(id)
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                val data :PeopleRemote? = response.body()
                data?.let {
                    database.peopleDao.insertAll(it)
                } ?: throw IllegalStateException("Body is null")
                return@withContext data
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}

interface  PeopleRepository {

suspend fun getPeople(id : Int) : PeopleRemote?

    companion object {
        /**
         * Singleton for the interface [EpisodeRepository]
         */
        val instance: PeopleRepository by lazy {
            // Lazy means "When I need it" so here this block will be launch
            // the first time you need the instance,
            // then, the reference will be stored in the value `instance`
            PeopleRepositoryImpl(
                HttpClientManager.instance.createApi(),
                DatabaseManager.getInstance().database
            )
        }

    }
}