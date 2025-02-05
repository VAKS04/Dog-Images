package com.example.dogsimage.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImagesDao {
    @Insert
    suspend fun insertImage(vararg images: Images)

    @Query("SELECT * FROM Images")
    fun getAllImages(): LiveData<List<Images?>>

    @Delete
    suspend fun deleteImage(images: Images)
}