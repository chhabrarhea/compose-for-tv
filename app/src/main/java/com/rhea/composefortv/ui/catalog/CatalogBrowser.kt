package com.rhea.composefortv.ui.catalog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rhea.composefortv.data.models.Movie

@Composable
fun CatalogBrowser(
  modifier: Modifier = Modifier,
  catalogBrowserViewModel: CatalogBrowserViewModel = hiltViewModel(),
  onMovieSelected: (Movie) -> Unit = {}
) {
}