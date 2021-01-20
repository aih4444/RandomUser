package com.test.randomuser_mvvm_test.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface API_Service {

    @GET(Constants.API)
    fun getRandomUserList(@Query("results") results: String?, @Query("seed") seed: String?):
            Deferred<NetworkUserContainer>
}