package com.example.filmsapp.api

import com.example.filmsapp.movie_details_model.MovieDetails
import com.example.filmsapp.popular_movies_model.Movies
import com.example.filmsapp.top_rated_movie_model.Rating
import com.example.filmsapp.trailers_model.TrailerResponse
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

    @GET("search/movie")
    fun getSearch(
        @Query("api_key") api_key: String,
        @Query("query") name: String,
    ): Call<Movies>

    companion object {

        var BASE_URL = "https://api.themoviedb.org/3/"
        var API_KEY = "1f49a5f345e0c857c3814334f71e360d"

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