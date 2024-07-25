package com.leehj.cleanarchitecture.di

import com.leehj.data.datasource.WordInfoLocalSourceImpl
import com.leehj.data.datasource.WordInfoRemoteSourceImpl
import com.leehj.data.local.WordInfoDao
import com.leehj.data.remote.DictionaryApi
import com.leehj.data.repository.WordInfoRepositoryImpl
import com.leehj.domain.repository.WordInfoRepository
import com.leehj.domain.usecase.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo =
        GetWordInfo(repository)

    @Provides
    @Singleton
    fun provideGetWordInfoRepository(
        localDataSource: WordInfoLocalSourceImpl,
        remoteDataSource: WordInfoRemoteSourceImpl): WordInfoRepository =
        WordInfoRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    @Singleton
    fun provideLocalWordInfoDataSource(dao: WordInfoDao): WordInfoLocalSourceImpl =
        WordInfoLocalSourceImpl(dao)

    @Provides
    @Singleton
    fun provideRemoteWordInfoDataSource(api: DictionaryApi): WordInfoRemoteSourceImpl =
        WordInfoRemoteSourceImpl(api)
}
