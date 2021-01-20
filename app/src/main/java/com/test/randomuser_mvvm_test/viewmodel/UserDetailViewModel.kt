package com.test.randomuser_mvvm_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.randomuser_mvvm_test.database.DatabaseUser
import com.test.randomuser_mvvm_test.domain.User
import com.test.randomuser_mvvm_test.utils.OpenForTesting

@OpenForTesting
class UserDetailViewModel: ViewModel() {
    // The internal MutableLiveData for the selected user
    private val _selectedUser = MutableLiveData<User>()

    // The external LiveData for the Selected user
    val selectedUser: LiveData<User>
        get() = _selectedUser


    fun setProperty(userProperty: User){
        _selectedUser.value = userProperty
    }
}