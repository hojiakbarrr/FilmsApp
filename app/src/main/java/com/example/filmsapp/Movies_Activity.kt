package com.example.filmsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        recyclerview.layoutManager = GridLayoutManager(this,2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemViewModel(R.drawable.ic_launcher_foreground, "Item " + i))
        }
        // This will pass the ArrayList to our Adapter



//        val apiInterface = ApiInterface.create().getMovies("1f49a5f345e0c857c3814334f71e360d")
        val apiInterface = ApiInterface.create().getTopRatedMovies ("1f49a5f345e0c857c3814334f71e360d")

        //apiInterface.enqueue( Callback<List<Movie>>())
        apiInterface.enqueue( object : Callback<Rating>, CustomAdapter.ItemClickListener {
            override fun onResponse(call: Call<Rating>?, response: Response<Rating>?) {
                val adapter = CustomAdapter(response?.body()?.results,this)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
                Log.d("testLogs","OnResponse Success ${response?.body()?.results}")
//                if(response?.body() != null)
//                    recyclerAdapter.setMovieListItems(response.body()!!)
            }

            override fun onFailure(call: Call<Rating>?, t: Throwable?) {
                Log.d("testLogs","OnResponse Success ${t?.message}")

            }

            override fun onItemClick(id: Int) {
                Toast.makeText(this@Movies_Activity,"click $id", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Movies_Activity, MovieDetailsActivity::class.java)
                intent.putExtra("id",id)
                startActivity(intent)            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()


    }
}

