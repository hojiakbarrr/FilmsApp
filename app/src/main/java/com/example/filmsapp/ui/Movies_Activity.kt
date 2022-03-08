package com.example.filmsapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.unknown.ItemViewModel
import com.example.filmsapp.R
import com.example.filmsapp.adapters.CustomAdapter
import com.example.filmsapp.api.ApiInterface
import com.example.filmsapp.popular_movies_model.Movies
import kotlinx.android.synthetic.main.activity_movies.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Movies_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)


        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this, 2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemViewModel(R.drawable.ic_launcher_foreground, "Item " + i))
        }
        // This will pass the ArrayList to our Adapter

        poisk.setOnClickListener {

            val text = searchFilms.text

            val apiInterfaceSearch =
                ApiInterface.create().getSearch(ApiInterface.API_KEY,text.toString())

            apiInterfaceSearch.enqueue(object : Callback<Movies>, CustomAdapter.ItemClickListener {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    val adapter = CustomAdapter(response?.body()?.results, this)
                    recyclerview.adapter = adapter
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(id: Int) {
                    val intent = Intent(this@Movies_Activity, MovieDetailsActivity::class.java)
                    intent.putExtra("id", id)
                    startActivity(intent)
                }
            })
        }


        val apiInterface = ApiInterface.create().getMovies("1f49a5f345e0c857c3814334f71e360d")
//        val apiInterface = ApiInterface.create().getTopRatedMovies ("1f49a5f345e0c857c3814334f71e360d")

        apiInterface.enqueue(object : Callback<Movies>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Movies>?, response: Response<Movies>?) {
                val adapter = CustomAdapter(response?.body()?.results, this)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
                Log.d("testLogs", "OnResponse Success ${response?.body()?.results}")
            }

            override fun onFailure(call: Call<Movies>?, t: Throwable?) {
                Log.d("testLogs", "OnResponse Success ${t?.message}")

            }

            override fun onItemClick(id: Int) {
                Toast.makeText(this@Movies_Activity, "click $id", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Movies_Activity, MovieDetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()


    }
}

