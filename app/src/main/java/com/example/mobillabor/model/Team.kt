package com.example.mobillabor.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "teams")
data class Team (
        @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id")
        @SerializedName("id") var id: Int?,
        @SerializedName("name") var name: String,
        @SerializedName("website") var website: String,
        @SerializedName("crestUrl") var crestUrl: String,
        @SerializedName("founded") var founded: Int,
        @SerializedName("clubColors") var clubColors: String,
        @SerializedName("venue") var venue: String,

){
    @SerializedName("squad") @Ignore var squad: ArrayList<Player> = ArrayList()

}