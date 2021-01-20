package com.test.randomuser_mvvm_test.di

import com.test.randomuser_mvvm_test.network.API_Service
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideUserApi(retrofit: Retrofit): API_Service {
        return retrofit.create(API_Service::class.java)
    }

    single { provideUserApi(get()) }
}