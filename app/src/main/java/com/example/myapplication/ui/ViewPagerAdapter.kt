package com.example.myapplication.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.ui.movie.MovieFragment
import com.example.myapplication.ui.tvshow.TvShowFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    companion object {
        const val MOVIE_TAB = 0
        const val TVSHOW_TAB = 1

    }
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            MOVIE_TAB -> MovieFragment()
            else -> TvShowFragment()
        }
    }
}
