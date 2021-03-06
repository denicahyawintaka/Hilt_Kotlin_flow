package com.example.myapplication.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemAdapterBinding
import com.example.myapplication.model.Movie
import com.example.myapplication.model.Movie.Companion.POSTER_URL
import com.example.myapplication.ui.ViewPagerAdapter
import com.example.myapplication.ui.detail.DetailActivity

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemAdapterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val itemState = getItem(position)
        holder.bind(itemState)
    }
}

class MovieViewHolder(private val binding: ItemAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie) {
        binding.movieTitle.text = movie.title
        binding.rating.text = movie.vote_average.toString()
        binding.originalTitle.text = movie.original_title
        val imageUrl = POSTER_URL + movie.poster_path
        Glide.with(binding.root).load(imageUrl).into(binding.moviePoster)
        binding.root.setOnClickListener {
            binding.root.context.apply {
                startActivity(
                    Intent(
                        this,
                        DetailActivity::class.java
                    ).apply {
                        putExtra(DetailActivity.DATA_EXTRA, movie.id)
                        putExtra(DetailActivity.FRAGMENT_EXTRA, ViewPagerAdapter.MOVIE_TAB)
                    }
                )
            }
        }
    }
}

class MovieDiff : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}
