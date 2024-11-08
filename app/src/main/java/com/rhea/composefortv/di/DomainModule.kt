package com.rhea.composefortv.di

import android.content.Context
import com.rhea.composefortv.data.remote.ApiService
import com.rhea.composefortv.domain.NetworkBoundResource
import com.rhea.composefortv.domain.repo.MovieRepository
import com.rhea.composefortv.domain.repo.MovieRepositoryImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {
  @Provides
  fun provideNetworkBoundResource(
    @IODispatcher ioDispatcher: CoroutineDispatcher,
  ): NetworkBoundResource {
    return NetworkBoundResource(ioDispatcher)
  }

  @Provides
  fun provideMovieRepository(
    apiService: ApiService,
    networkBoundResource: NetworkBoundResource,
    @ApplicationContext appContext: Context
  ): MovieRepository {
    return MovieRepositoryImpl(apiService, networkBoundResource, appContext)
  }
}