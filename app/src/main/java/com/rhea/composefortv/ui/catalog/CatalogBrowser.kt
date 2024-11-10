package com.rhea.composefortv.ui.catalog

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Text
import com.rhea.composefortv.data.models.Category
import com.rhea.composefortv.data.models.Movie
import com.rhea.composefortv.domain.model.Resource

@Composable
fun CatalogBrowser(
  modifier: Modifier = Modifier,
  catalogBrowserViewModel: CatalogBrowserViewModel = hiltViewModel(),
  onMovieSelected: (Movie) -> Unit = {}
) {
  val movieResource = catalogBrowserViewModel.allMovies.collectAsStateWithLifecycle()

  when (movieResource.value) {
    is Resource.Loading -> {
      // Show a loading indicator
    }

    is Resource.Success -> {
      CatalogBrowserContent(
        trays = movieResource.value.data.orEmpty(),
        onMovieSelected = onMovieSelected
      )
    }

    is Resource.Error -> {
      // Show an error message
    }
  }
}

@Composable
fun CatalogBrowserContent(
  trays: List<Category>,
  onMovieSelected: (Movie) -> Unit
) {
  LazyColumn {
    items(trays) { category ->
      Text(text = category.title)
      LazyRow {
        items(category.movies) { movie ->
          MovieCard(movie = movie, onClick = onMovieSelected)
        }
      }
    }
  }
}