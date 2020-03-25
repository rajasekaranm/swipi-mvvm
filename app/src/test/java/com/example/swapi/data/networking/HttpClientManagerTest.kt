package com.example.swapi.data.networking

import com.example.swapi.BuildConfig
import com.example.swapi.data.networking.api.PeopleAPI
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HttpClientManagerTest {


    private lateinit var instance: HttpClientManager

    @Before
    fun setUp() {
        instance = HttpClientManager.instance
    }

    @Test
    fun getRetrofit() {
        instance.retrofit.apply {
            Assert.assertEquals(
                "Should be the same base url",
                this.baseUrl().toString(),
                BuildConfig.BASE_URL
            )
            Assert.assertNotNull(
                "Should be a GsonConverter Factory",
                this.converterFactories().firstOrNull { it is GsonConverterFactory }
            )
        }
    }

    @Test
    fun createApiTest() {
        instance.createApi<PeopleAPI>().apply {
            Assert.assertTrue("It Succeed", true)
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Create api test without interface as API`() {
        instance.createApi<Retrofit>()
    }

}