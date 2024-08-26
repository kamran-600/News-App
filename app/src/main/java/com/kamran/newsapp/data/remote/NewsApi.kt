package com.kamran.newsapp.data.remote

import com.kamran.newsapp.data.remote.dto.NewsResponse
import com.kamran.newsapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apikey") apikey: String = API_KEY,

        ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apikey") apikey: String = API_KEY,
        )
}