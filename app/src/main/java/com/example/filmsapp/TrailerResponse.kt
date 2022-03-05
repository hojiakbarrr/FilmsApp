package com.example.filmsapp

import com.google.gson.annotations.SerializedName


data class TrailerResponse(
        @SerializedName("id")
        val id: Int,
        @SerializedName("results")
        val trailerList: List<DataTrailer>,
    )

