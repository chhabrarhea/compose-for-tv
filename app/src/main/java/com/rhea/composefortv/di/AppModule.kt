package com.rhea.composefortv.di

import com.rhea.composefortv.data.remote.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
      .baseUrl("https://api.themoviedb.org/3/")
      .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
      .build()
  }

  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
  }
}