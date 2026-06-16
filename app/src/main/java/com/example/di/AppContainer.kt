package com.example.di

import android.content.Context
import com.example.data.local.NewsDatabase
import com.example.data.local.UserPreferencesRepository
import com.example.data.remote.AuthInterceptor
import com.example.data.remote.NewsApiService
import com.example.data.repository.NewsRepositoryImpl
import com.example.domain.repository.NewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class AppContainer(private val context: Context) {
    
    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepository(context)
    }

    private val database: NewsDatabase by lazy {
        NewsDatabase.getDatabase(context)
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val okHttpClient: OkHttpClient by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val apiService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }

    val newsRepository: NewsRepository by lazy {
        NewsRepositoryImpl(apiService, database.newsDao)
    }
}
