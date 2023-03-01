package com.cookandroid.lottecinema_clone_project.main.data

import com.google.gson.annotations.SerializedName

data class Dates(
    @SerializedName("maximum") val maximum: String,
    @SerializedName("minimum") val minimum: String
)
