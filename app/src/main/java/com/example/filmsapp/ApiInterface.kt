package com.example.filmsapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("movie/popular")
    fun getMovies(@Query("api_key") sort: String): Call<Movies>

    @GET("movie/{movie_id}")
    fun getMoviesDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") sort: String,
    ): Call<MovieDetails>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") sort: String): Call<Rating>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") id: Int,
        @Query("api_key") sort: String

    ): Call<TrailerResponse>

    companion object {

        var BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): ApiInterface {
            val httpLoginInterceptor = HttpLoggingInterceptor()
            httpLoginInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient =
                OkHttpClient.Builder()
                    .addInterceptor(httpLoginInterceptor)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}