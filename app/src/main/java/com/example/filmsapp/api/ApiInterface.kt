package com.example.filmsapp.api

import com.example.filmsapp.actor_details_model.actor_details_model
import com.example.filmsapp.actors_model.Actors_model
import com.example.filmsapp.movie_details_model.MovieDetails
import com.example.filmsapp.movies_model.Movies
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
    fun getPopularMovies(@Query("api_key") sort: String): Call<Movies>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") sort: String): Call<Movies>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") sort: String): Call<Movies>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("api_key") sort: String): Call<Movies>

    @GET("movie/latest")
    fun getLatestMovies(@Query("api_key") sort: String): Call<Movies>

    @GET("movie/{movie_id}")
    fun getMoviesDetails(
        @Path("movie_id") movieId: Int,
        @Query("language")language:String,
        @Query("api_key") sort: String,
    ): Call<MovieDetails>

    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Path("movie_id") id: Int,
        @Query("language") language: String,
        @Query("api_key") sort: String

    ): Call<TrailerResponse>

    @GET("search/movie")
    fun getSearch(
        @Query("api_key") api_key: String,
        @Query("query") name: String,
    ): Call<Movies>

    @GET("person/popular")
    fun getPerson(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<Actors_model>

    @GET("search/person")
    fun getSearchActor(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("query") name: String,
        ): Call<Actors_model>

    @GET("person/{person_id}")
    fun getActorssDetails(
        @Path("person_id") personId: Int,
        @Query("language")language:String,
        @Query("api_key") sort: String,
    ): Call<actor_details_model>


    companion object {

        var BASE_URL = "https://api.themoviedb.org/3/"
        var API_KEY = "1f49a5f345e0c857c3814334f71e360d"
        var RU = "ru"

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