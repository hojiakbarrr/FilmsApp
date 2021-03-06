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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.actors_model.ActorsResponce
import com.example.filmsapp.adapters.Actors_adapter
import com.example.filmsapp.api.ApiInterface
import com.example.filmsapp.ui.Actors_Detail_Activity
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
        val recyclerview = v.findViewById<RecyclerView>(R.id.movie)
        recyclerview.layoutManager = GridLayoutManager(requireContext(), 2)
        val ok = v.poiskActor

        val api = ApiInterface.create().getPerson(ApiInterface.API_KEY, ApiInterface.RU)

        api.enqueue(object : Callback<ActorsResponce>, Actors_adapter.ItemClickListener {

            override fun onResponse(call: Call<ActorsResponce>, response: Response<ActorsResponce>) {

                val adapter = Actors_adapter(response.body()?.results, this)
                recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<ActorsResponce>, t: Throwable) {
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



        ok.setOnClickListener {
            val text = searchActors.text
            if (text.length == 0 || text.length < 3){
                Toast.makeText(requireContext(), "???????? ?????????? ???? ???????????? ???????? ????????????", Toast.LENGTH_SHORT).show()
            }else {
                val apiInter =
                    ApiInterface.create().getSearchActor(ApiInterface.API_KEY, ApiInterface.RU,
                        text.toString())
                apiInter.enqueue(object : Callback<ActorsResponce>, Actors_adapter.ItemClickListener {

                    override fun onResponse(
                        call: Call<ActorsResponce>,
                        response: Response<ActorsResponce>,
                    ) {

                        val adapter = Actors_adapter(response.body()?.results, this)
                        recyclerview.adapter = adapter
                    }

                    override fun onFailure(call: Call<ActorsResponce>, t: Throwable) {
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


        }


        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActorsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}