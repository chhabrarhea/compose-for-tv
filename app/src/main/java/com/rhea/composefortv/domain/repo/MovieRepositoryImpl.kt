package com.rhea.composefortv.domain.repo

import android.content.Context
import com.rhea.composefortv.data.models.CastResponse
import com.rhea.composefortv.data.models.Movie
import com.rhea.composefortv.data.models.MovieDetail
import com.rhea.composefortv.data.models.Movies
import com.rhea.composefortv.data.remote.ApiService
import com.rhea.composefortv.domain.NetworkBoundResource
import com.rhea.composefortv.domain.model.Resource
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
  private val apiService: ApiService,
  private val networkBoundResource: NetworkBoundResource,
  @ApplicationContext private val context: Context
): MovieRepository {
  override suspend fun getMovieDetails(id: Int): Resource<MovieDetail> {
    return networkBoundResource.invoke {
      apiService.getMovieDetails(id, "api_key")
    }
  }

  override suspend fun getMovieCast(id: Int): Resource<CastResponse> {
    return networkBoundResource.invoke {
      apiService.getMovieCast(id, "api_key")
    }
  }

  override suspend fun getMovies(): Resource<Movies> {
    return try {
      context.assets.open("curated_trays.json").use { inputStream ->
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(Movies::class.java)
        val movies = adapter.fromJson(bufferedReader.use { it.readText() })
        movies?.let {
          Resource.Success(it)
        } ?: Resource.Error(Exception("Parsing error"))
      }
    } catch (e: Exception) {
      Resource.Error(e)
    }
  }
}