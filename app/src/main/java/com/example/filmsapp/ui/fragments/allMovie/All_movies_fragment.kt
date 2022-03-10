package com.example.filmsapp.ui.fragments.allMovie

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.adapters.CustomAdapter
import com.example.filmsapp.api.ApiInterface
import com.example.filmsapp.movies_model.MovieResponce
import com.example.filmsapp.ui.MovieDetailsActivity
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

        val spinner = v.spMonth as Spinner
        val button = v.poisk

        val recyclerview = v.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)

        val customList = listOf("Жанры","Предстоящий","Рейтинговые","Самые популярные","Сейчас смотрят")
        val adapter = ArrayAdapter<String>(requireContext(), com.google.android.material.R.layout.support_simple_spinner_dropdown_item,customList )
        spinner.adapter = adapter


        button.setOnClickListener {

            val text = searchFilms.text
            if (text.length == 0 || text.length < 3){
                Toast.makeText(requireContext(), "поля ввода не должно быть пустым", Toast.LENGTH_SHORT).show()
            }else{
                val apiInterfaceSearch =
                    ApiInterface.create().getSearch(ApiInterface.API_KEY, text.toString())

                apiInterfaceSearch.enqueue(object : Callback<MovieResponce>, CustomAdapter.ItemClickListener {
                    override fun onResponse(call: Call<MovieResponce>, response: Response<MovieResponce>) {
                        val adapter = CustomAdapter(response?.body()?.results, this)
                        recyclerview.adapter = adapter
                    }

                    override fun onFailure(call: Call<MovieResponce>, t: Throwable) {
                        Toast.makeText(requireContext(), "ничего не найдено", Toast.LENGTH_SHORT).show()
                    }

                    override fun onItemClick(id: Int) {
                        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                })
            }

        }



        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long, ) {
                if (adapterView!!.getItemAtPosition(position) == "Рейтинговые") {
                    val face = ApiInterface.create().getTopRatedMovies(ApiInterface.API_KEY)
                    face.enqueue(object : Callback<MovieResponce>, CustomAdapter.ItemClickListener {
                        override fun onResponse(call: Call<MovieResponce>?, response: Response<MovieResponce>?) {
                            val adapter = CustomAdapter(response?.body()?.results, this)

                            // Setting the Adapter with the recyclerview
                            recyclerview.adapter = adapter
                            Log.d("testLogs", "OnResponse Success ${response?.body()?.results}")
                        }

                        override fun onFailure(call: Call<MovieResponce>?, t: Throwable?) {
                            Log.d("testLogs", "OnResponse Success ${t?.message}")

                        }

                        override fun onItemClick(id: Int) {
                            Toast.makeText(requireContext(), "click $id", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)
                        }
                    })
                }
                else if (adapterView!!.getItemAtPosition(position) == "Сейчас смотрят"){
                    val api = ApiInterface.create().getNowPlayingMovies(ApiInterface.API_KEY)

                    api.enqueue(object : Callback<MovieResponce>, CustomAdapter.ItemClickListener{
                        override fun onResponse(call: Call<MovieResponce>, response: Response<MovieResponce>) {
                            val adapter = CustomAdapter(response?.body()?.results, this)

                            recyclerview.adapter = adapter                        }

                        override fun onFailure(call: Call<MovieResponce>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemClick(id: Int) {
                            Toast.makeText(requireContext(), "click $id", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)                        }
                    })

                }
                else if (adapterView!!.getItemAtPosition(position) == "Предстоящий"){
                    val api = ApiInterface.create().getUpcomingMovies(ApiInterface.API_KEY)
                    api.enqueue(object : Callback<MovieResponce>, CustomAdapter.ItemClickListener{
                        override fun onResponse(call: Call<MovieResponce>, response: Response<MovieResponce>) {
                            val adapter = CustomAdapter(response?.body()?.results, this)

                            recyclerview.adapter = adapter                        }

                        override fun onFailure(call: Call<MovieResponce>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemClick(id: Int) {
                            Toast.makeText(requireContext(), "click $id", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)                        }
                    })
                }
                else if (adapterView!!.getItemAtPosition(position) == "Самые популярные"){
                    val api = ApiInterface.create().getPopularMovies(ApiInterface.API_KEY)
                    api.enqueue(object : Callback<MovieResponce>, CustomAdapter.ItemClickListener{
                        override fun onResponse(call: Call<MovieResponce>, response: Response<MovieResponce>) {
                            val adapter = CustomAdapter(response?.body()?.results, this)

                            recyclerview.adapter = adapter                        }

                        override fun onFailure(call: Call<MovieResponce>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemClick(id: Int) {
                            Toast.makeText(requireContext(), "click $id", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)                        }
                    })
                }
                else if (adapterView!!.getItemAtPosition(position) == "Жанры"){
                    val api = ApiInterface.create().getPopularMovies(ApiInterface.API_KEY)
                    api.enqueue(object : Callback<MovieResponce>, CustomAdapter.ItemClickListener{
                        override fun onResponse(call: Call<MovieResponce>, response: Response<MovieResponce>) {
                            val adapter = CustomAdapter(response?.body()?.results, this)

                            recyclerview.adapter = adapter                        }

                        override fun onFailure(call: Call<MovieResponce>, t: Throwable) {
                            TODO("Not yet implemented")
                        }

                        override fun onItemClick(id: Int) {
                            Toast.makeText(requireContext(), "click $id", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
                            intent.putExtra("id", id)
                            startActivity(intent)                        }
                    })
                }
                Toast.makeText(requireContext(),
                    "click ${adapterView!!.getItemAtPosition(position)}",
                    Toast.LENGTH_SHORT).show()
            }

        }

        return v
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllMoviesFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}