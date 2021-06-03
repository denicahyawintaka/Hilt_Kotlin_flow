package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.ViewPagerAdapter.Companion.MOVIE_TAB
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupTab()
    }

    private fun setupTab() {
        viewPagerAdapter = ViewPagerAdapter(this)
        binding.pager.adapter = viewPagerAdapter

        TabLayoutMediator(
            binding.tabLayout, binding.pager
        ) { tab, position ->
            when (position) {
                MOVIE_TAB -> {
                    tab.text = getString(R.string.movie_tab)
                }
                else -> tab.text = getString(R.string.tvshow_tab)
            }
        }.attach()
    }
}
