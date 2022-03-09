package com.example.filmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.adapters.AdapterTrailer
import com.example.filmsapp.api.ApiInterface
import com.example.filmsapp.movie_details_model.MovieDetails
import com.example.filmsapp.trailers_model.TrailerResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overView: TextView
    private lateinit var trailer: RecyclerView

    private val trailerAdapter: AdapterTrailer by lazy(LazyThreadSafetyMode.NONE) {
        AdapterTrailer()
    }
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val id = intent.getIntExtra("id", 0)
        Log.d("testing", "id $id")

        title = findViewById(R.id.movies_details_title)
        releaseDate = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movies_details_score)
        overView = findViewById(R.id.movies_details_body_overview)
        banner = findViewById(R.id.movies_details_image_banner)

        trailer = findViewById(R.id.rcTrailer)
        trailer.adapter = trailerAdapter


        val responseMovie =
            ApiInterface.create().getMoviesDetails(id,"ru", "1f49a5f345e0c857c3814334f71e360d")
        val responseMovieTrailer = ApiInterface.create().getMovieTrailer(id,ApiInterface.RU,"1f49a5f345e0c857c3814334f71e360d")

        responseMovie.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {

                title.text = response!!.body()!!.title
                releaseDate.text = response.body()!!.release_date
                score.text = response.body()!!.vote_average.toString()
                overView.text = response.body()!!.overview

                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + response.body()!!.poster_path)
                    .into(banner)



                Log.d("testLogs", "OnResponse Success ${response?.body()?.title}")
            }

            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                Log.d("testLogs", "OnResponse Success ${t?.message}")

            }


        })

        responseMovieTrailer.enqueue(object : Callback<TrailerResponse> {
            override fun onResponse(
                call: Call<TrailerResponse>,
                response: Response<TrailerResponse>,
            ) {
                if (response.isSuccessful){
                    trailerAdapter.trailerList = response.body()!!.trailerList
                }else{
                    Toast.makeText(this@MovieDetailsActivity,response.message().toString(),Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
                Log.d("testLogs", "OnResponse Success ${t?.message}")
            }

        })




    }
}