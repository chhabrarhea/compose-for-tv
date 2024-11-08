package com.rhea.composefortv.ui.catalog

import androidx.lifecycle.ViewModel
import com.rhea.composefortv.data.models.Movies
import com.rhea.composefortv.domain.model.Resource
import com.rhea.composefortv.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CatalogBrowserViewModel @Inject constructor(
  private val movieRepository: MovieRepository
): ViewModel() {

  private val _allMovies = MutableStateFlow<Resource<Movies>>(Resource.Loading())
  private val allMovies = _allMovies.asStateFlow()


  init {
    fetchAllMovies()
  }

  private fun fetchAllMovies() {

  }
}