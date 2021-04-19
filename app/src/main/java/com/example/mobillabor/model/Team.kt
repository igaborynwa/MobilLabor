package com.example.mobillabor.model

import com.google.gson.annotations.SerializedName

data class Team (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("website") val website: String,
    @SerializedName("crestUrl") val crestUrl: String,
    @SerializedName("founded") val founded: Int,
    @SerializedName("clubColors") val clubColors: String,
    @SerializedName("venue") val venue: String,
    @SerializedName("squad") val squad: List<Player>
)