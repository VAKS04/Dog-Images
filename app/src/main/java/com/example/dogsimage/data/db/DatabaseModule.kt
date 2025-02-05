package com.example.dogsimage.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DatabaseModule {

    @Provides
    @ViewModelScoped
    fun getDatabase(@ApplicationContext context: Context):DogsImagesDatabase{
        val instance = Room.databaseBuilder(
            context.applicationContext,
            DogsImagesDatabase::class.java,
            "Dogs_Images"
        ).build()
        return instance
    }
}