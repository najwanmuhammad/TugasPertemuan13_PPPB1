package com.example.tugaspertemuan13

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaspertemuan13.database.AppDatabase
import com.example.tugaspertemuan13.database.MovieDao
import com.example.tugaspertemuan13.database.MovieEntity
import com.example.tugaspertemuan13.databinding.FragmentHomeBinding
import com.example.tugaspertemuan13.model.Movie
import com.example.tugaspertemuan13.model.MovieMapper
import com.example.tugaspertemuan13.model.MovieResponse
import com.example.tugaspertemuan13.network.ApiClient
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieDao: MovieDao
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        movieDao = AppDatabase.getInstance(requireContext()).movieDao()
        setupRecyclerView()
        fetchMovies()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(emptyList(), { movie ->
            // Handle item click
        }, { movie ->
            lifecycleScope.launch {
                val entity = MovieMapper.toEntity(movie)
                movie.isFavorite = !movie.isFavorite
                if (movie.isFavorite) {
                    movieDao.insertMovie(entity)
                    Toast.makeText(requireContext(), "Film ditambahkan ke favorite", Toast.LENGTH_SHORT).show()
                } else {
                    movieDao.deleteMovieById(movie.id ?: return@launch)
                    Toast.makeText(requireContext(), "Film dihapus dari favorite", Toast.LENGTH_SHORT).show()
                }
                adapter.notifyDataSetChanged() // Perbarui data di RecyclerView
            }
        })
        binding.rvMovie.adapter = adapter
        binding.rvMovie.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun fetchMovies() {
        lifecycleScope.launch {
            try {
                val response = ApiClient.getInstance().getMovieList("542cd33e1a2ff9c331fea41dc6d6f198")
                response.results.forEach { movie ->
                    Log.d("PosterPath", "URL Poster: https://image.tmdb.org/t/p/w500${movie.posterPath}")
                    Log.d("MovieData", "ID: ${movie.id}, Title: ${movie.title}, PosterPath: ${movie.posterPath}")
                }
                adapter.updateMovies(response.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}


