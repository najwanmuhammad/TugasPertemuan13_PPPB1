package com.example.tugaspertemuan13.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("SELECT * FROM movie")
    fun getFavoriteMoviesFlow(): Flow<List<MovieEntity>>

    @Query("DELETE FROM movie WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: String)
}
