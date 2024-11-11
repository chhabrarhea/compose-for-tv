package com.rhea.composefortv.ui.catalog

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.tv.material3.Text
import com.rhea.composefortv.data.models.Category
import com.rhea.composefortv.data.models.Movie
import com.rhea.composefortv.domain.model.Resource
import com.rhea.composefortv.ui.theme.Typography

@Composable
fun CatalogBrowser(
  modifier: Modifier = Modifier,
  catalogBrowserViewModel: CatalogBrowserViewModel = hiltViewModel(),
  onMovieSelected: (Movie) -> Unit = {}
) {
  val movieResource = catalogBrowserViewModel.allMovies.collectAsStateWithLifecycle()

  Log.d("Rhea", "CatalogBrowser: ${movieResource.value}")

  when (movieResource.value) {
    is Resource.Loading -> {
      // Show a loading indicator
    }

    is Resource.Success -> {
      Log.d("Rhea", "CatalogBrowser: ${movieResource.value.data}")
      CatalogBrowserContent(
        trays = movieResource.value.data?.list.orEmpty(),
        onMovieSelected = onMovieSelected
      )
    }

    is Resource.Error -> {
      Log.d("Rhea", "CatalogBrowser: ${movieResource.value.error}")
      // Show an error message
    }
  }
}

@Composable
fun CatalogBrowserContent(
  trays: List<Category>,
  onMovieSelected: (Movie) -> Unit
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    contentPadding = PaddingValues(horizontal = 48.dp, vertical = 32.dp)
  ) {
    items(trays) { category ->
      Text(
        text = category.title,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        style = Typography.headlineMedium
      )
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp)
      ) {
        items(category.movies) { movie ->
          MovieCard(movie = movie, onClick = onMovieSelected)
        }
      }
    }
  }
}