package com.leehj.cleanarchitecture.di

import com.leehj.data.remote.DictionaryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlin.jvm.Throws

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(getLoggingInterceptor())
//            .addInterceptor(DelayedResponseInterceptor(5000)) // 응답 지연 상황 테스트를 위해 추가
            .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .client(okHttpClient)
            .client(provideHttpClient())
            .addConverterFactory(gsonConverterFactory)
            .build()

    @Provides
    @Singleton
    fun provideConverterFactory() : GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    fun provideDictionaryApiService(retrofit: Retrofit): DictionaryApi =
        retrofit.create(DictionaryApi::class.java)

    private fun getLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

}

/*
    응답 지연 테스트를 위한 클래스
 */
class DelayedResponseInterceptor(private val delay: Long): Interceptor{
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Thread.sleep(delay)
        return chain.proceed(request)
    }
}