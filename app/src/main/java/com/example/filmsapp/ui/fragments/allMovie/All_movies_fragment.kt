package com.example.filmsapp.ui.fragments.allMovie

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.adapters.CustomAdapter
import com.example.filmsapp.api.ApiInterface
import com.example.filmsapp.popular_movies_model.Movies
import com.example.filmsapp.ui.MovieDetailsActivity
import com.example.filmsapp.unknown.ItemViewModel
import kotlinx.android.synthetic.main.all_movies_fragment_fragment.*
import kotlinx.android.synthetic.main.all_movies_fragment_fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class All_movies_fragment : Fragment() {

    companion object {
        fun newInstance() = All_movies_fragment()
    }

    private lateinit var viewModel: AllMoviesFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.all_movies_fragment_fragment, container, false)


        // getting the recyclerview by its id
        val recyclerview = v.findViewById<RecyclerView>(R.id.recyclerView)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(ItemViewModel(R.drawable.ic_launcher_foreground, "Item " + i))
        }
        // This will pass the ArrayList to our Adapter

        val but = v.poisk

        but.setOnClickListener {

            val text = searchFilms.text

            val apiInterfaceSearch =
                ApiInterface.create().getSearch(ApiInterface.API_KEY, text.toString())

            apiInterfaceSearch.enqueue(object : Callback<Movies>, CustomAdapter.ItemClickListener {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    val adapter = CustomAdapter(response?.body()?.results, this)
                    recyclerview.adapter = adapter
                }

                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(id: Int) {
                    val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
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
                Toast.makeText(requireContext(), "click $id", Toast.LENGTH_SHORT).show()
                val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        })


        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllMoviesFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}