package com.example.movietest.model.remote

import com.example.movietest.common.BASE_URL
import com.example.movietest.common.END_POINT
import com.example.movietest.model.MovieList
import com.example.movietest.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesService {
    @GET(END_POINT)
    fun getMovies(): Call<MovieList>// Call<List<Movi>

    companion object{
        fun initRetrofit(): MoviesService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesService::class.java)
        }
    }
}