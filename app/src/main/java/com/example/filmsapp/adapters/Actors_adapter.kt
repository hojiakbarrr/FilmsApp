package com.example.filmsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.actors_model.Result
import com.squareup.picasso.Picasso


class Actors_adapter(private val mList: List<Result>?, val mItemclickListener: Actors_adapter.ItemClickListener, ) : RecyclerView.Adapter<Actors_adapter.ActorsViewHolder>() {
    interface ItemClickListener {
        fun onItemClick(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, ): Actors_adapter.ActorsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actors_item, parent, false)
        return ActorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: Actors_adapter.ActorsViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList?.get(position)?.profile_path).into(holder.imageView)
        holder.textView.text = mList!![position].name

    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class ActorsViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.foto_actor)
        val textView: TextView = itemView.findViewById(R.id.name_actor)

        init {
            ItemView.setOnClickListener {
                mList?.get(position)?.let { it -> mItemclickListener.onItemClick(it.id) }
            }
        }

    }

}

