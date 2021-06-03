package com.example.myapplication.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.setContentFragment
import com.example.myapplication.ui.ViewPagerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val DATA_EXTRA = "dataExtra"
        const val FRAGMENT_EXTRA = "fragmentExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.detail_page)
        intent.extras?.getInt(FRAGMENT_EXTRA)?.let {
            if (it == ViewPagerAdapter.MOVIE_TAB) {
                setContentFragment(savedInstanceState, MovieDetailFragment.createInstance())
            } else {
                setContentFragment(savedInstanceState, TvShowDetailFragment.createInstance())
            }
        }
    }
}
