package com.example.filmsapp.ui.favorites_fragment

import android.annotation.SuppressLint
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
import com.example.filmsapp.database.*
import com.example.filmsapp.ui.Actors_Detail_Activity
import com.example.filmsapp.ui.MovieDetailsActivity
import kotlinx.android.synthetic.main.actors__fragment.*

class Favorites_Fragment : Fragment(),
    Movie_base_Adapter.ItemClickListener, Actor_base_Adapter.ItemClickListener {


    private lateinit var viewModel: FavoritesViewModel
    private lateinit var recmovie: RecyclerView
    private lateinit var recactor: RecyclerView
    private  var adaptor =  Movie_base_Adapter(this)
    private var adaptor2 = Actor_base_Adapter(this)

    private var listMovie = mutableListOf<Movie_model_base>()
    private var listPerson = mutableListOf<Actor_model_base>()


    override fun onStart() {
        super.onStart()

        listMovie = MovieDataBase.getDatabaseInstance(requireContext()).allMovies().getAllMovies()!!
        adaptor.mList  = listMovie

        listPerson = ActorDataBase.getDatabaseInstance(requireContext()).allActors().getAllActor()!!
        adaptor2.mList = listPerson


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val rr = inflater.inflate(R.layout.favorites__fragment, container, false)
        Log.i("fefe,","onCreateView")

        recmovie = rr.findViewById(R.id.rec_fav_movie)
        recactor = rr.findViewById(R.id.fav_recycl_actor)

        recmovie.layoutManager = GridLayoutManager(requireContext(), 2)
        recactor.layoutManager = GridLayoutManager(requireContext(), 2)
        recmovie.adapter = adaptor
        recactor.adapter = adaptor2

//        adaptor2 = Actor_base_Adapter(listPerson, this)
//        recactor.adapter = adaptor2
//        adaptor2.notifyDataSetChanged()
//        recactor.adapter?.notifyDataSetChanged()
//        recactor.invalidate()



        return rr
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onItemClick(id: Int) {
        val intent = Intent(requireContext(), MovieDetailsActivity::class.java)
        Toast.makeText(requireActivity(), "click $id", Toast.LENGTH_SHORT).show()
        Log.d("testLogs", "OnResponse Success $id")
        intent.putExtra("id", id)
        startActivity(intent)
    }

    override fun onActorClick(id: Int) {
        val intent = Intent(requireContext(), Actors_Detail_Activity::class.java)
        intent.putExtra("idi", id)
        startActivity(intent)
    }

    override fun delete(actor: Actor_model_base) {
        ActorDataBase.getDatabaseInstance(requireContext()).allActors().delete_Actor(actor)
        listPerson = ActorDataBase.getDatabaseInstance(requireContext()).allActors().getAllActor()!!
        adaptor2.mList = listPerson
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun delete(movie: Movie_model_base) {
        MovieDataBase.getDatabaseInstance(requireContext()).allMovies().delete_Movie(movie)
        listMovie = MovieDataBase.getDatabaseInstance(requireContext()).allMovies().getAllMovies()!!
        adaptor.mList  = listMovie
    }


}