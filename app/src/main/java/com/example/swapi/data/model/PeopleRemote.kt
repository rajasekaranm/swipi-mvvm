package com.example.swapi.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "people")
data class PeopleRemote @JvmOverloads constructor(

	@Ignore
	@field:SerializedName("films")
	var films: List<String?>? = null,

	@field:SerializedName("homeworld")
	var homeworld: String? = null,

	@field:SerializedName("gender")
	var gender: String? = null,

	@field:SerializedName("skin_color")
	var skinColor: String? = null,

	@field:SerializedName("edited")
	var edited: String? = null,

	@PrimaryKey
	@field:SerializedName("created")
	var created: String,

	@field:SerializedName("mass")
	var mass: String? = null,

	@Ignore
	@field:SerializedName("vehicles")
	var vehicles: List<String?>? = null,

	@field:SerializedName("url")
	var url: String? = null,

	@field:SerializedName("hair_color")
	var hairColor: String? = null,

	@field:SerializedName("birth_year")
	var birthYear: String? = null,

	@field:SerializedName("eye_color")
	var eyeColor: String? = null,

	@Ignore
	@field:SerializedName("species")
	var species: List<String?>? = null,

	@Ignore
	@field:SerializedName("starships")
	var starships: List<String?>? = null,

	@field:SerializedName("name")
	var name: String? = null,

	@field:SerializedName("height")
	var height: String? = null
)