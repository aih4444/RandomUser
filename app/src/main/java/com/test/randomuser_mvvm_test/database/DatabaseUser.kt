package com.test.randomuser_mvvm_test.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.randomuser_mvvm_test.domain.User

@Entity
data class DatabaseUser(
    var id: String,
    var title: String,
    var first: String,
    var last: String,
    @PrimaryKey
    var email: String,
    var picture: String,
    var location: String
)
fun List<DatabaseUser>.asDomainModel():List<User> {
    return map {
        User(
            id = it.id,
            title = it.title,
            first = it.first,
            last = it.last,
            email = it.email,
            picture = it.picture,
            location = it.location
        )
    }
}