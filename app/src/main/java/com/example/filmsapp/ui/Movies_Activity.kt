package com.example.filmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.filmsapp.R
import com.example.filmsapp.adapters.ViewPagerAdapters
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_movies.*

class Movies_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val tablayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager_2)
        val adapter=ViewPagerAdapters(supportFragmentManager,lifecycle)

        viewPager2.adapter = adapter

        TabLayoutMediator(tab_layout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.setIcon(R.drawable.movie)
                    tab.text = "All Movies"
                }
                1->{
                    tab.setIcon(R.drawable.person)
                    tab.text = "Actors"
                }
                2->{
                    tab.setIcon(R.drawable.favorite)
                    tab.text = "Favorites"
                }
            }
        }.attach()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finishAffinity()


    }
}

