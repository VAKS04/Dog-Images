package com.example.dogsimage.domain

import androidx.lifecycle.asFlow
import com.example.dogsimage.data.db.DogsImagesDatabase
import com.example.dogsimage.data.db.Images
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val database: DogsImagesDatabase
) {

    val allImages: StateFlow<List<Images?>> = database.imagesDao().getAllImages().asFlow().stateIn(
        scope = CoroutineScope(Dispatchers.IO),
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    suspend fun insertImages(images: Images){
        database.imagesDao().insertImage(images)
    }

    suspend fun deleteImages(images:Images){
        database.imagesDao().deleteImage(images)
    }
}