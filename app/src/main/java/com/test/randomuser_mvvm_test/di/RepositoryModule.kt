package com.test.randomuser_mvvm_test.di

import com.test.randomuser_mvvm_test.database.UserDatabase
import com.test.randomuser_mvvm_test.network.API_Service
import com.test.randomuser_mvvm_test.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(api: API_Service, dao: UserDatabase): UserRepository {
        return UserRepository(api, dao)
    }

    single {
        provideRepository(get(), get())
    }
}