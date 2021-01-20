package com.test.randomuser_mvvm_test.util

import com.test.randomuser_mvvm_test.database.DatabaseUser


object FakeObjectsUtils{
val DBDatabaseCharacter = DatabaseUser(id = "1",  title = "Mr" , first = "Nadir" , last = "da Rocha",
    email = "nadir.darocha@example.com", picture = "https://randomuser.me/api/portraits/men/87.jpg"
, location = "Ourinhos")

val listDBDatabaseCharacter = listOf(DBDatabaseCharacter)
}