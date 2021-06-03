package com.example.myapplication.ui.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemAdapterBinding
import com.example.myapplication.model.Movie
import com.example.myapplication.model.TvShow
import com.example.myapplication.ui.ViewPagerAdapter
import com.example.myapplication.ui.detail.DetailActivity

class TvShowAdapter : ListAdapter<TvShow, TvShowViewHolder>(MovieDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemAdapterBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val itemState = getItem(position)
        holder.bind(itemState)
    }
}

class TvShowViewHolder(private val binding: ItemAdapterBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(tvShow: TvShow) {
        binding.movieTitle.text = tvShow.name
        binding.rating.text = tvShow.vote_average.toString()
        binding.originalTitle.text = tvShow.original_name
        val imageUrl = Movie.POSTER_URL + tvShow.poster_path
        Glide.with(binding.root).load(imageUrl).into(binding.moviePoster)
        binding.root.setOnClickListener {
            binding.root.context.apply {
                startActivity(
                    Intent(
                        this,
                        DetailActivity::class.java
                    ).apply {
                        putExtra(DetailActivity.DATA_EXTRA, tvShow.id)
                        putExtra(DetailActivity.FRAGMENT_EXTRA, ViewPagerAdapter.TVSHOW_TAB)
                    }
                )
            }
        }
    }
}

class MovieDiff : DiffUtil.ItemCallback<TvShow>() {
    override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
        return oldItem == newItem
    }

}
