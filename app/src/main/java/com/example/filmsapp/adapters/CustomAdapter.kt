package com.example.filmsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.movies_model.ResultMovie
import com.squareup.picasso.Picasso

class CustomAdapter(private val mList: List<ResultMovie>?, val mItemclickListener: ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_xml_file, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList?.get(position)?.poster_path).into(holder.imageView)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return this.mList!!.size
    }

    // Holds the views for adding it to image and text
   inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
//        val textView: TextView = itemView.findViewById(R.id.textView)
        init {
            ItemView.setOnClickListener {
                mList?.get(position)?.let { it -> mItemclickListener.onItemClick(it.id) }
            }
        }

    }

}