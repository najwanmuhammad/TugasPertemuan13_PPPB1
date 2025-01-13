package com.example.tugaspertemuan13.network

import com.example.tugaspertemuan13.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovieList(@Query("api_key") apiKey: String): MovieResponse
}
