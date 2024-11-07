package com.rhea.composefortv.domain.repo

import com.rhea.composefortv.data.models.CastResponse
import com.rhea.composefortv.data.models.Movie
import com.rhea.composefortv.data.models.MovieDetail
import com.rhea.composefortv.domain.model.Resource

interface MovieRepository {
  suspend fun getMovieDetails(id: Int): Resource<MovieDetail>
  suspend fun getMovieCast(id: Int): Resource<CastResponse>
  suspend fun getMovies(): Resource<List<Movie>>
}