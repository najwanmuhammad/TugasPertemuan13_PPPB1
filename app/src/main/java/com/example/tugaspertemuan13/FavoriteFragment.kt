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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugaspertemuan13.database.AppDatabase
import com.example.tugaspertemuan13.database.MovieDao
import com.example.tugaspertemuan13.database.MovieEntity
import com.example.tugaspertemuan13.databinding.FragmentFavoriteBinding
import com.example.tugaspertemuan13.model.Movie
import com.example.tugaspertemuan13.model.MovieMapper
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var movieDao: MovieDao
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        movieDao = AppDatabase.getInstance(requireContext()).movieDao()
        setupRecyclerView()
        observeFavorites()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = MovieAdapter(emptyList(), {}, { movie ->
            lifecycleScope.launch {
                movieDao.deleteMovieById(movie.id ?: return@launch)
                Toast.makeText(requireContext(), "Film berhasil dihapus dari favorite", Toast.LENGTH_SHORT).show()
            }
        })
        binding.rvFavoriteMovies.adapter = adapter
        binding.rvFavoriteMovies.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeFavorites() {
        lifecycleScope.launch {
            movieDao.getFavoriteMoviesFlow().collect { movies ->
                // Konversi MovieEntity ke Movie dengan status isFavorite yang sesuai
                val mappedMovies = movies.map { entity ->
                    MovieMapper.toMovie(entity).apply { isFavorite = true } // Pastikan isFavorite = true
                }
                adapter.updateMovies(mappedMovies)
            }
        }
    }


}