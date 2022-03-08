package com.example.filmsapp.ui.actors_fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.actors.Actors_model
import com.example.filmsapp.adapters.Actors_adapter
import com.example.filmsapp.api.ApiInterface
import com.example.filmsapp.ui.Actors_Detail_Activity
import com.example.filmsapp.ui.MovieDetailsActivity
import kotlinx.android.synthetic.main.actors__fragment.*
import kotlinx.android.synthetic.main.actors__fragment.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Actors_Fragment : Fragment() {

    companion object {
        fun newInstance() = Actors_Fragment()
    }

    private lateinit var viewModel: ActorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val v = inflater.inflate(R.layout.actors__fragment, container, false)

        val recyclerview = v.findViewById<RecyclerView>(R.id.recActors)

        recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)

        val api = ApiInterface.create().getPerson(ApiInterface.API_KEY, ApiInterface.RU)

        api.enqueue(object : Callback<Actors_model>, Actors_adapter.ItemClickListener {

            override fun onResponse(call: Call<Actors_model>, response: Response<Actors_model>) {

                val adapter = Actors_adapter(response.body()?.results, this)
                recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<Actors_model>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(id: Int) {
            val intent = Intent(requireContext(), Actors_Detail_Activity::class.java)
                    Toast.makeText(requireActivity(), "click $id", Toast.LENGTH_SHORT).show()
                    Log.d("testLogs", "OnResponse Success $id")
                    intent.putExtra("idi", id)
                    startActivity(intent)

            }

        })


        val ok = v.poiskActor

        ok.setOnClickListener {
            val text = searchActors.text

            val apiInter =
                ApiInterface.create().getSearchActor(ApiInterface.API_KEY, ApiInterface.RU,
                    text.toString())
            apiInter.enqueue(object : Callback<Actors_model>, Actors_adapter.ItemClickListener {

                override fun onResponse(
                    call: Call<Actors_model>,
                    response: Response<Actors_model>,
                ) {

                    val adapter = Actors_adapter(response.body()?.results, this)
                    recyclerview.adapter = adapter
                }

                override fun onFailure(call: Call<Actors_model>, t: Throwable) {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(id: Int) {
                    val intent = Intent(requireContext(), Actors_Detail_Activity::class.java)
                    Toast.makeText(requireActivity(), "click $id", Toast.LENGTH_SHORT).show()
                    Log.d("testLogs", "OnResponse Success $id")
                    intent.putExtra("idi", id)
                    startActivity(intent)
                }

            })


        }


        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}