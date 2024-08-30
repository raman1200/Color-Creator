package com.raman.project.colorcreator.di

import android.content.Context
import com.raman.project.colorcreator.db.AppDatabase
import com.raman.project.colorcreator.db.ColorDao
import com.raman.project.colorcreator.repositories.ColorRepository
import com.raman.project.colorcreator.viewmodels.ColorViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideColorRepository(colorDao: ColorDao): ColorRepository {
        return ColorRepository(colorDao)
    }

    @Provides
    @Singleton
    fun provideColorViewModel(colorRepository: ColorRepository): ColorViewModel {
        return ColorViewModel(colorRepository)
    }


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideColorDao(database: AppDatabase): ColorDao {
        return database.colorDao()
    }


}