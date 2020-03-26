package com.example.swapi.data.networking.api

import com.example.swapi.data.networking.HttpClientManager
import com.example.swapi.data.networking.createApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class PeopleAPITest {

    private lateinit var instance: HttpClientManager
    private lateinit var api: PeopleAPI


    @Before
    fun setUp() {
        instance = HttpClientManager.instance
        api = instance.createApi()
    }

    @Test
    fun getPeople() = runBlocking {
           api.getPeople("https://swapi.co/api/people/3/").apply {
              Assert.assertTrue("Request must be a success", this.isSuccessful)
               println("test success for people api")
         }
        return@runBlocking
    }

}