package com.example.swapi.data.networking.api

import com.example.swapi.data.model.PeopleRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PeopleAPI {

    @GET(GET_PEOPLE)
    suspend fun getPeople(
        @Path("id") id: Int
    ): Response<PeopleRemote>


    companion object{
        const val GET_PEOPLE="people/{id}/"
    }
}