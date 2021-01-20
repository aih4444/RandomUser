package com.test.randomuser_mvvm_test.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.randomuser_mvvm_test.database.DatabaseUser

@JsonClass(generateAdapter = true)
data class NetworkUserContainer(val results: List<ResultsItem>)

data class Id(

    @Json(name="name")
    val name: String,

    @Json(name="value")
    val value: String
)

data class Info(

    @Json(name="seed")
    val seed: String? = null,

    @Json(name="page")
    val page: Int? = null,

    @Json(name="results")
    val results: Int? = null,

    @Json(name="version")
    val version: String? = null
)

data class Street(

    @Json(name="number")
    val number: Int,

    @Json(name="name")
    val name: String
)

data class Dob(

    @Json(name="date")
    val date: String? = null,

    @Json(name="age")
    val age: Int? = null
)

data class Login(

    @Json(name="sha1")
    val sha1: String? = null,

    @Json(name="password")
    val password: String? = null,

    @Json(name="salt")
    val salt: String? = null,

    @Json(name="sha256")
    val sha256: String? = null,

    @Json(name="uuid")
    val uuid: String? = null,

    @Json(name="username")
    val username: String? = null,

    @Json(name="md5")
    val md5: String? = null
)

data class Registered(

    @Json(name="date")
    val date: String? = null,

    @Json(name="age")
    val age: Int? = null
)

data class Location(

    @Json(name="country")
    val country: String,

    @Json(name="city")
    val city: String,

    @Json(name="street")
    val street: Street,

    @Json(name="timezone")
    val timezone: Timezone,

    @Json(name="postcode")
    val postcode: String,

    @Json(name="coordinates")
    val coordinates: Coordinates,

    @Json(name="state")
    val state: String
)

data class ResultsItem(

    @Json(name="nat")
    val nat: String,

    @Json(name="gender")
    val gender: String,

    @Json(name="phone")
    val phone: String,

    @Json(name="dob")
    val dob: Dob,

    @Json(name="name")
    val name: Name,

    @Json(name="registered")
    val registered: Registered,

    @Json(name="location")
    val location: Location,

    @Json(name="id")
    var id: Id,

    @Json(name="login")
    val login: Login,

    @Json(name="cell")
    val cell: String,

    @Json(name="email")
    val email: String,

    @Json(name="picture")
    val picture: Picture
)

data class Name(

    @Json(name="last")
    val last: String,

    @Json(name="title")
    val title: String,

    @Json(name="first")
    val first: String
)

data class Picture(

    @Json(name="thumbnail")
    val thumbnail: String,

    @Json(name="large")
    val large: String,

    @Json(name="medium")
    val medium: String
)

data class Coordinates(

    @Json(name="latitude")
    val latitude: String? = null,

    @Json(name="longitude")
    val longitude: String? = null
)

data class Timezone(

    @Json(name="offset")
    val offset: String? = null,

    @Json(name="description")
    val description: String? = null
)

fun NetworkUserContainer.asDatabaseModel() : List<DatabaseUser> {
    return results.map {
        DatabaseUser(
            id = it.id.name,
            title = it.name.title,
            first = it.name.first,
            last = it.name.last,
            email = it.email,
            picture = it.picture.large,
            location = it.location.street.name


        )
    }
}