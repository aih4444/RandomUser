package com.test.randomuser_mvvm_test.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.test.randomuser_mvvm_test.database.DatabaseUser
import com.test.randomuser_mvvm_test.database.UserDao
import com.test.randomuser_mvvm_test.database.UserDatabase
import com.test.randomuser_mvvm_test.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()
        dao =database.userDao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertJoke() = runBlockingTest{
        val jokeItem = DatabaseUser("1A","Me","james","boughtpn","james@gmail.com","https://randomuser.me/api/portraits/men/87.jpg","1 elmwood")

        dao.insertAll(listOf(jokeItem))

        val allJoke = dao.getLocalDBUsers().getOrAwaitValue()

        assertThat(allJoke).contains(jokeItem)
    }
}