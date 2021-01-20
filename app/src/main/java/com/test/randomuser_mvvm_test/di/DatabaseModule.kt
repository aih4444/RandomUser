package com.test.randomuser_mvvm_test.di

import android.app.Application
import androidx.room.Room
import com.test.randomuser_mvvm_test.database.UserDatabase


import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

//Module: returns instance of the class (loose coupling)
val databaseModule = module {
    fun providesDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(application, UserDatabase::class.java, "users.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    // singleton: single instance
    single { providesDatabase(androidApplication()) } // lifecycle of the application
}