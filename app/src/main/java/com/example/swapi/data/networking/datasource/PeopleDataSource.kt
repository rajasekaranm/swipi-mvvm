package com.example.swapi.data.networking.datasource

import android.util.Log
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.swapi.data.model.PeopleRemote
import com.example.swapi.data.networking.api.PeopleAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PeopleDataSource private constructor(
    private val api: PeopleAPI,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, PeopleRemote>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PeopleRemote>
    ) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = api.getPeopleList(page = FIRST_KEY).run {
                    if (this.isSuccessful) this.body()
                        ?: throw IllegalStateException("Body is null")
                    else throw IllegalStateException("Response is not successful : code = ${this.code()}")
                }
                if (params.placeholdersEnabled) callback.onResult(
                    response.results,
                    0,
                    response.results.size,
                    null,
                    if (!response.next.isNullOrEmpty()) FIRST_KEY + 1 else null
                ) else callback.onResult(
                    response.results,
                    null,
                    if (!response.next.isNullOrEmpty()) FIRST_KEY + 1 else null
                )

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PeopleRemote>) {
        scope.launch(Dispatchers.IO) {
            try {
                val response = api.getPeopleList(page = params.key).run {
                    if (this.isSuccessful) this.body()
                        ?: throw IllegalStateException("Body is null")
                    else throw IllegalStateException("Response is not successful : code = ${this.code()}")
                }
                callback.onResult(
                    response.results,
                    if (!response.next.isNullOrEmpty()) params.key + 1 else null
                )
            } catch (e: Exception) {
                Log.e(TAG, "loadInitial: ", e)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PeopleRemote>) =
        Unit

    companion object {
        private const val TAG: String = "PeopleDataSource"
        private const val FIRST_KEY = 1
    }


    class Factory(
        private val api: PeopleAPI,
        private val scope: CoroutineScope
    ) : DataSource.Factory<Int, PeopleRemote>() {
        override fun create(): DataSource<Int, PeopleRemote> =
            PeopleDataSource(
                api, scope
            )
    }}