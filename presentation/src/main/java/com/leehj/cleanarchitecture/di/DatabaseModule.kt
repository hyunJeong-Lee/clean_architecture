package com.leehj.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.leehj.data.local.Converters
import com.leehj.data.local.WordInfoDao
import com.leehj.data.local.WordInfoDatabase
import com.plcoding.dictionary.feature_dictionary.data.util.GsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideWordInfoDao(database: WordInfoDatabase) : WordInfoDao =
        database.dao

    @Provides
    @Singleton
    fun provideWordDatabase(@ApplicationContext context: Context): WordInfoDatabase =
        Room.databaseBuilder(
            context,
            WordInfoDatabase::class.java,
            "word_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .fallbackToDestructiveMigration()
            .build()
}