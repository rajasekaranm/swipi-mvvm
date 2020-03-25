package com.example.swapi.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.swapi.SwapiApplication
import com.example.swapi.data.database.DatabaseManager
import com.example.swapi.data.model.PeopleRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

class PeopleRepositoryTest {

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()
    private val testDispatcher = newSingleThreadContext("UI context")
    private lateinit var repository: PeopleRepository
    private val people = PeopleRemote(
        name= "Luke Skywalker",
    height= "172",
    mass= "77",
    hairColor= "blond",
    skinColor= "fair",
    eyeColor= "blue",
    birthYear= "19BBY",
    gender= "male",
    homeworld= "https://swapi.co/api/planets/1/",
    films= listOf(
    "https://swapi.co/api/films/2/",
    "https://swapi.co/api/films/6/",
    "https://swapi.co/api/films/3/",
    "https://swapi.co/api/films/1/",
    "https://swapi.co/api/films/7/"
    ),
    species= listOf(
    "https://swapi.co/api/species/1/"
    ),
        vehicles = listOf(
    "https://swapi.co/api/vehicles/14/",
    "https://swapi.co/api/vehicles/30/"
    ),
        starships= listOf(
    "https://swapi.co/api/starships/12/",
    "https://swapi.co/api/starships/22/"
    ),
    created= "2014-12-09T13:50:51.644000Z",
    edited= "2014-12-20T21:17:56.891000Z",
    url= "https://swapi.co/api/people/1/"
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        DatabaseManager.getInstance(SwapiApplication())
        repository = PeopleRepository.instance
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.close()
    }

    @Test
    fun getPeople() = runBlocking{
        val value = repository.getPeople(1)
        Assert.assertEquals("Should be people", people, value)
    }
}