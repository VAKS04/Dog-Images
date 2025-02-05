package com.example.dogsimage.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Images(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "link") val link: String
)