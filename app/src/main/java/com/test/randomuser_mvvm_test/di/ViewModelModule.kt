package com.test.randomuser_mvvm_test.di


import com.test.randomuser_mvvm_test.viewmodel.UserDetailViewModel
import com.test.randomuser_mvvm_test.viewmodel.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { UserViewModel(get()) }

    viewModel { UserDetailViewModel() }
}