package com.example.filmsapp.database

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmsapp.R
import com.example.filmsapp.actors_model.Actor
import com.squareup.picasso.Picasso

class Actor_base_Adapter( val mItemclickListener: Actor_base_Adapter.ItemClickListener): RecyclerView.Adapter<Actor_base_Adapter.ViewHolder>() {
    interface ItemClickListener {
        fun onActorClick(id: Int)
        fun delete(actor: Actor_model_base)
    }

    var mList:List<Actor_model_base> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue){
            field=newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): Actor_base_Adapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.actor_delete_item, parent, false)
        return ViewHolder(view)    }

    override fun onBindViewHolder(holder: Actor_base_Adapter.ViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + mList.get(position).profile_path).into(holder.imageView)
        holder.textView.text = mList[position].name    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.foto_actor)
        val textView: TextView = itemView.findViewById(R.id.name_actor)
        val deleteMovie: ImageView = itemView.findViewById(R.id.deleteblack)

        init {
            ItemView.setOnClickListener {
                mList.get(position).let { it -> mItemclickListener.onActorClick(it.id) }
            }

        }
        init {
            deleteMovie.setOnClickListener {
                mList.get(position).let { it -> mItemclickListener.delete(it) }
            }
        }

    }

}