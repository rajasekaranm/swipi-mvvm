package com.example.swapi.data.networking.api

import com.example.swapi.data.model.PeoplePaginatedResult
import com.example.swapi.data.model.PeopleRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface PeopleAPI {

    @GET
    suspend fun getPeople(
        @Url url: String
    ): Response<PeopleRemote>

    @GET(GET_PEOPLE_LIST)
    suspend fun getPeopleList(
        @Query("page") page: Int
    ): Response<PeoplePaginatedResult<PeopleRemote>>


    companion object {
        const val GET_PEOPLE_LIST = "people/"
    }
}