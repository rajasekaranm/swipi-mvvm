package com.example.swapi.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.swapi.data.database.DatabaseManager
import com.example.swapi.data.database.SwapiDatabase
import com.example.swapi.data.model.PeopleRemote
import com.example.swapi.data.networking.HttpClientManager
import com.example.swapi.data.networking.api.PeopleAPI
import com.example.swapi.data.networking.createApi
import com.example.swapi.data.networking.datasource.PeopleDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PeopleRepositoryImpl(private val api: PeopleAPI, private val database: SwapiDatabase) :
    PeopleRepository {

    private val paginationConfig = PagedList.Config
        .Builder()
        // If you set true you will have to catch
        // the place holder case in the adapter
        .setEnablePlaceholders(false)
        .setPageSize(10)
        .build()


    override suspend fun getPeople(url: String): PeopleRemote? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPeople(url)
                check(response.isSuccessful) { "Response is not a success : code = ${response.code()}" }
                val data: PeopleRemote? = response.body()
                data?.let {
//                    database.peopleDao.insertAll(it)
                } ?: throw IllegalStateException("Body is null")
                return@withContext data
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    override fun getPeoplePagedList(scope: CoroutineScope): LiveData<PagedList<PeopleRemote>> {
        return LivePagedListBuilder(
            PeopleDataSource.Factory(api, scope),
            paginationConfig
        ).build()
    }
}

interface PeopleRepository {

    suspend fun getPeople(url: String): PeopleRemote?

    fun getPeoplePagedList(scope: CoroutineScope): LiveData<PagedList<PeopleRemote>>

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