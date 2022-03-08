package com.example.filmsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmsapp.ui.actors_fragments.Actors_Fragment
import com.example.filmsapp.ui.favorites_fragment.Favorites_Fragment
import com.example.filmsapp.ui.fragments.allMovie.All_movies_fragment

class ViewPagerAdapters(fragmentManager: FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
            0->{
                All_movies_fragment()
            }
            1->{
                Actors_Fragment()
            }
            2->{
                Favorites_Fragment()
            }
            else->{
                Fragment()
            }
        }
    }


}