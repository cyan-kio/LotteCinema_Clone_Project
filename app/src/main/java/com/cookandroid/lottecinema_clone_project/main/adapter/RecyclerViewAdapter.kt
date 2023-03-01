package com.cookandroid.lottecinema_clone_project.main.adapter

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.cookandroid.lottecinema_clone_project.R
import com.cookandroid.lottecinema_clone_project.databinding.ItemRecyclerPopularMovieBinding
import com.cookandroid.lottecinema_clone_project.databinding.ItemRecyclerReviewBinding
import com.cookandroid.lottecinema_clone_project.main.data.Movie
import com.cookandroid.lottecinema_clone_project.main.data.Review
import com.cookandroid.lottecinema_clone_project.main.fragment.DetailFragment

class MovieChartAdapter(
    var context: Context,
    var popularMovies: ArrayList<Movie>,
    val fragmentManager : FragmentManager,
    val detailFragment: DetailFragment
) : RecyclerView.Adapter<MovieChartAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemRecyclerPopularMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, position: Int) {
            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w154/"+movie.poster_path)
                .transform(CenterCrop())
                .into(binding.imgPopularPoster)
            binding.apply {
                tvPopularPosterNum.text = (position + 1).toString()
                tvPopularPopularity.text = "흥행도 "+ Math.round(movie.popularity).toString()
                tvPopularTitle.text = movie.title
                tvPopularVote.text = movie.vote_average.toString()
            }
            binding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("movie_id", movie.id.toString())
                detailFragment.arguments = bundle
                fragmentManager.beginTransaction().replace(R.id.fragmentView, detailFragment).commit()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(popularMovies[position], position)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }
}