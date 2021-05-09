package com.example.mobillabor.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "players")
data class Player (
        @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("dateOfBirth") var dateOfBirth: String,
    @SerializedName("countryOfBirth") var countryOfBirth: String,
    @SerializedName("nationality") var nationality: String,
    @SerializedName("position") var position: String?

        )