package com.example.filmsapp.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.squareup.picasso.Picasso

class Movie_base_Adapter (private val mList: List<Movie_model_base>?, val mItemclickListener: Movie_base_Adapter.ItemClickListener) :
    RecyclerView.Adapter<Movie_base_Adapter.ViewHolder>(){
    interface ItemClickListener {
        fun onItemClick(id: Int)
        fun delete(movie: Movie_model_base)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Movie_base_Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.delete_movie, parent, false)

        return ViewHolder(view)    }

    override fun onBindViewHolder(holder: Movie_base_Adapter.ViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList?.get(position)?.poster_path).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return this.mList!!.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.delete)
        val deleteMovie: ImageView = itemView.findViewById(R.id.udalutimage)
        init {
            ItemView.setOnClickListener {
                mList?.get(position)?.let { it -> mItemclickListener.onItemClick(it.id) }
            }
        }
        init {
            deleteMovie.setOnClickListener {
                mList?.get(position)?.let { it -> mItemclickListener.delete(it) }
            }
        }



    }

}