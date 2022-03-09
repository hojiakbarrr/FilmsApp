package com.example.filmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.filmsapp.R
import com.example.filmsapp.actor_details_model.actor_details_model
import com.example.filmsapp.api.ApiInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Actors_Detail_Activity : AppCompatActivity() {
    private lateinit var banner: ImageView
    private lateinit var name: TextView
    private lateinit var birthday: TextView
    private lateinit var place: TextView
    private lateinit var biogrophy: TextView
    private lateinit var iddd: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actors_detail)

        val id = intent.getIntExtra("idi", 0)
        Log.d("testing", "idi $id")

        banner = findViewById(R.id.profile)
        name = findViewById(R.id.nameOfActor)
        birthday = findViewById(R.id.days)
        place = findViewById(R.id.place)
        biogrophy = findViewById(R.id.biography)
        iddd = findViewById(R.id.id_db)

        val response = ApiInterface.create().getActorssDetails(id,ApiInterface.RU,ApiInterface.API_KEY)

        response.enqueue(object  : Callback<actor_details_model>{
            override fun onResponse(
                call: Call<actor_details_model>,
                response: Response<actor_details_model>,
            ) {

                name.text = response.body()!!.name
                birthday.text = response.body()!!.birthday
                place.text = response.body()!!.place_of_birth
                biogrophy.text = response.body()!!.biography
                iddd.text = response.body()!!.imdb_id
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + response.body()!!.profile_path)
                    .into(banner)
            }

            override fun onFailure(call: Call<actor_details_model>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })

    }
}