package com.example.tugaspertemuan13.model

import com.example.tugaspertemuan13.database.MovieEntity

object MovieMapper {
    fun toEntity(movie: Movie): MovieEntity {
        return MovieEntity(
            id = movie.id ?: "",
            title = movie.title ?: "",
            overview = movie.overview ?: "",
            posterPath = movie.posterPath ?: "",
            isFavorite = movie.isFavorite
        )
    }

    fun toMovie(entity: MovieEntity): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            posterPath = entity.posterPath,
            isFavorite = entity.isFavorite
        )
    }
}