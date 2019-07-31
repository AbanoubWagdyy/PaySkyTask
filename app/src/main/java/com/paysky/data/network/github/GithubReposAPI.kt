package com.paysky.data.network.github

import com.paysky.data.model.Repository
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubReposAPI {
    @GET("repos")
    fun getRepositories(
        @Query("page") page: Int,
        @Query("items_per_page") items_per_page: Int
    ): Flowable<List<Repository>>
}
