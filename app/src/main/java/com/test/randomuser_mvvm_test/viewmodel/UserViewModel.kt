package com.test.randomuser_mvvm_test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.randomuser_mvvm_test.database.DatabaseUser
import com.test.randomuser_mvvm_test.domain.User
import com.test.randomuser_mvvm_test.repository.UserRepository
import com.test.randomuser_mvvm_test.utils.LoadingState
import com.test.randomuser_mvvm_test.utils.OpenForTesting
import kotlinx.coroutines.*
import java.lang.Exception

@OpenForTesting
class UserViewModel(private val userRepository: UserRepository): ViewModel() {

    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState>
        get() = _loadingState


    private val viewModelJob = SupervisorJob()

    private val viewModelScope  = CoroutineScope(viewModelJob + Dispatchers.Main)


    val userResults = userRepository.results

    init {
        refreshFromRepository()
    }

    fun refreshFromRepository(){
        viewModelScope.launch {
            try {
                _loadingState.value = LoadingState.LOADING
                userRepository.refreshUser()
                _loadingState.value = LoadingState.LOADED
            }
            catch(networkError: Exception){
                _loadingState.value = LoadingState.error(networkError.message)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    //for display data in second fragment
    // LiveData to handle navigation to the selected User
    private val _navigateToSelectedUser = MutableLiveData<User>()
    val navigateToSelectedUser: LiveData<User>
        get() = _navigateToSelectedUser

    /**
     * When the user is clicked, set the [_navigateToSelectedUser] [MutableLiveData]
     * @param userProperty The [User] that was clicked on.
     */

    fun displayUserDetails(userProperty: User) {
        _navigateToSelectedUser.value = userProperty
    }

    /**
     * After the navigation has taken place, make sure navigateToSelectedUser is set to null
     */
    fun displayUserDetailsComplete() {
        _navigateToSelectedUser.value = null
    }
}