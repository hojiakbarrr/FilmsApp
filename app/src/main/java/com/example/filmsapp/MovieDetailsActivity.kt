package com.example.filmsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var releaseDate: TextView
    private lateinit var score: TextView
    private lateinit var overView: TextView

    private lateinit var banner : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val id = intent.getIntExtra("id",0)
        Log.d("testing", "id $id")

        title = findViewById(R.id.movies_details_title)
        releaseDate = findViewById(R.id.movies_details_date)
        score = findViewById(R.id.movies_details_score)
        overView = findViewById(R.id.movies_details_body_overview)
        banner = findViewById(R.id.movies_details_image_banner)


//        val apiInterface = ApiInterface.create().getMovies("1f49a5f345e0c857c3814334f71e360d")
        val apiInterface = ApiInterface.create().getMoviesDetails(id,"1f49a5f345e0c857c3814334f71e360d")

        apiInterface.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>?, response: Response<MovieDetails>?) {

                // Setting the Adapter with the recyclerview

                title.text = response!!.body()!!.title
                releaseDate.text = response.body()!!.release_date
                score.text = response.body()!!.vote_average.toString()
                overView.text = response.body()!!.overview

                Picasso.get().load("https://image.tmdb.org/t/p/w500" + response.body()!!.poster_path).into(banner)

//                banner.setImageResource(response.body()!!.poster_path as Int)


                Log.d("testLogs", "OnResponse Success ${response?.body()?.title}")
//                if(response?.body() != null)
//                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<MovieDetails>?, t: Throwable?) {
                Log.d("testLogs", "OnResponse Success ${t?.message}")

            }


        })




    }
}