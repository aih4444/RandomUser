package com.test.randomuser_mvvm_test

import androidx.lifecycle.MediatorLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.test.randomuser_mvvm_test.database.DatabaseUser
import com.test.randomuser_mvvm_test.util.EspressoIdlingResourceRule
import com.test.randomuser_mvvm_test.util.TestUtils.withRecyclerView
import com.test.randomuser_mvvm_test.utils.LoadingState

import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Mock
import org.koin.test.KoinTest


@RunWith(AndroidJUnit4ClassRunner::class)
class UserView_FragmentTest : KoinTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, false)
    //block the test until we have the results...
    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()

    @Mock
    private lateinit var charactersListViewModel: UserView_Fragment

    val fakeLoadingState= MediatorLiveData<LoadingState>()

    val fakeNavigateToSelectedProperty = MediatorLiveData<DatabaseUser>()





    @Test
    fun test_mainActivityIsDisplayed() {
        launchActivity()
        emitLoadingState(LoadingState.LOADING)
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isCharacterListFragmentVisible() {
        launchActivity()
        emitLoadingState(LoadingState.LOADING)

        onView(withId(R.id.FirstFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isRecyclerViewVisible() {
        launchActivity()
        emitLoadingState(LoadingState.LOADING)

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isCharacterDataVisible() {
        launchActivity()

        emitLoadingState(LoadingState.LOADING)

        val strFirst: String = "Nadir"
        val strLast: String = "da Rocha"

        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.tv_first)).check(matches(withText(strFirst)))
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.tv_last)).check(matches(withText(strLast)))
        onView(withRecyclerView(R.id.recycler_view).atPositionOnView(0, R.id.image_thumbnail)).check(matches(isDisplayed()))

    }



    private fun emitNavigateToSelectedProperty(databaseCharacter: DatabaseUser){
        fakeNavigateToSelectedProperty.postValue(databaseCharacter)
    }
    private fun emitLoadingState(loadingState: LoadingState){
        fakeLoadingState.postValue(loadingState)
    }
    private fun launchActivity() {
        activityRule.launchActivity(null)
    }


}