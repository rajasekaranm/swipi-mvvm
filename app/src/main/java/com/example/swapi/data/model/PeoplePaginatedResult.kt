package com.example.swapi.data.model

import com.google.gson.annotations.SerializedName

data class PeoplePaginatedResult<T>(

    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<T>
)