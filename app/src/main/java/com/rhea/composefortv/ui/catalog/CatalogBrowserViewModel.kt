package com.rhea.composefortv.ui.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rhea.composefortv.data.models.Movies
import com.rhea.composefortv.di.IODispatcher
import com.rhea.composefortv.domain.model.Resource
import com.rhea.composefortv.domain.repo.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogBrowserViewModel @Inject constructor(
  private val movieRepository: MovieRepository,
  @IODispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {

  private val _allMovies = MutableStateFlow<Resource<Movies>>(Resource.Loading())
  val allMovies = _allMovies.asStateFlow()


  init {
    fetchAllMovies()
  }

  private fun fetchAllMovies() {
    viewModelScope.launch(ioDispatcher) {
      delay(2500)
      _allMovies.value = Resource.Error(Throwable("Failed to fetch movies"))
    }
  }

  fun retryAllMovies() {
    viewModelScope.launch {
      _allMovies.emit(Resource.Loading())
      fetchAllMovies()
    }
  }
}