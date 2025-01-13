package com.example.tugaspertemuan13.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey val id: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val isFavorite: Boolean
)

