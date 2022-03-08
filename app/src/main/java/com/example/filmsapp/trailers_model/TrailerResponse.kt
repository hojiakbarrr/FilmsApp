package com.example.filmsapp.trailers_model

import com.google.gson.annotations.SerializedName


data class TrailerResponse(
    @SerializedName("id")
        val id: Int,
    @SerializedName("results")
        val trailerList: List<DataTrailer>,
    )

