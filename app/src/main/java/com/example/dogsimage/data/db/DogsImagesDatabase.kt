package com.example.dogsimage.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Images::class], version = 1)
abstract class DogsImagesDatabase():RoomDatabase() {
    abstract fun imagesDao():ImagesDao
}