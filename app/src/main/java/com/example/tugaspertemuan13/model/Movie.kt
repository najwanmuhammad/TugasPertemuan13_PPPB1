package com.example.tugaspertemuan13.model

import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id")
    val id: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    var isFavorite: Boolean = false
)

