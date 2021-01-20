package com.test.randomuser_mvvm_test.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.test.randomuser_mvvm_test.database.UserDatabase
import com.test.randomuser_mvvm_test.database.asDomainModel
import com.test.randomuser_mvvm_test.domain.User
import com.test.randomuser_mvvm_test.network.API_Service
import com.test.randomuser_mvvm_test.network.asDatabaseModel
import com.test.randomuser_mvvm_test.utils.OpenForTesting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

@OpenForTesting
class UserRepository(private val userApiservices: API_Service, private val database: UserDatabase) {
    suspend fun refreshUser() {
        // worker thread to perform API request and saving data locally
        withContext(Dispatchers.IO) {
            Timber.d("Refresh user is called")
            val userList = userApiservices.getRandomUserList("200","yv").await()
            database.userDao.insertAll(userList.asDatabaseModel())
        }
    }

    val results: LiveData<List<User>> = Transformations.map(database.userDao.getLocalDBUsers()){
        it.asDomainModel()
    }

}