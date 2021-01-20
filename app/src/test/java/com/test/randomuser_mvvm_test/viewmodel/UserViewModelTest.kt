package com.test.randomuser_mvvm_test.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.test.randomuser_mvvm_test.LiveDataTestUtil
import com.test.randomuser_mvvm_test.MainCoroutineRule
import com.test.randomuser_mvvm_test.repository.UserRepository
import com.test.randomuser_mvvm_test.utils.LoadingState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

class UserViewModelTest {

    @Mock
    private lateinit var mRepo: UserRepository

    @get: Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    lateinit var userViewModel: UserViewModel

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    fun test_refreshDataFromRepository_do_what_it_should_do() = runBlocking {

        //prep
        userViewModel = UserViewModel(mRepo)

        val liveDataTestUtil = LiveDataTestUtil<LoadingState>()
        //action
        //when JokeViewModel is created refreshJokes is called.
        //verify
        Mockito.verify(mRepo).refreshUser()
        val dataEmitted = liveDataTestUtil.getValue(userViewModel.loadingState)
        assertEquals( LoadingState.Status.SUCCESS.name,dataEmitted?.status?.name)
    }

    @Test
    fun test_loading_error_is_called() = runBlocking {
        //prep
        //action
        //verify
        Mockito.`when`(mRepo.refreshUser()).thenThrow(RuntimeException())
        userViewModel = UserViewModel(mRepo)
        val mediatorLiveData = MediatorLiveData<LoadingState>()
        mediatorLiveData.addSource(userViewModel.loadingState){
            when(it.status){
                LoadingState.Status.RUNNING -> {
                    //ignore
                }
                else -> {
                    assertEquals( LoadingState.Status.FAILED.name,it?.status?.name)

                }
            }
        }
    }
}