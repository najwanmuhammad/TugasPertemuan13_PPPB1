package com.example.tugaspertemuan13

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tugaspertemuan13.databinding.MovieItemBinding
import com.example.tugaspertemuan13.model.Movie

class MovieAdapter(
    private var movies: List<Movie>,
    private val onItemClick: (Movie) -> Unit,
    private val onFavoriteClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, onItemClick: (Movie) -> Unit, onFavoriteClick: (Movie) -> Unit) {
            val posterUrl = movie.posterPath?.let {
                "https://image.tmdb.org/t/p/w500$it"
            }

            binding.movieTitle.text = movie.title
            binding.movieOverview.text = movie.overview
            Glide.with(binding.root.context)
                .load(posterUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(binding.moviePoster)

            // Set icon favorite berdasarkan status
            binding.movieFavorite.setImageResource(
                if (movie.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            )

            binding.root.setOnClickListener { onItemClick(movie) }

            binding.movieFavorite.setOnClickListener {
                onFavoriteClick(movie)
                // Perbarui ikon favorite setelah klik
                binding.movieFavorite.setImageResource(
                    if (movie.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], onItemClick, onFavoriteClick)
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(newMovies: List<Movie>) {
        movies = newMovies
        notifyDataSetChanged()
    }
}



