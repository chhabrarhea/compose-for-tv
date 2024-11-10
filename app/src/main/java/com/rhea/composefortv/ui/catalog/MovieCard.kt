package com.rhea.composefortv.ui.catalog

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Card
import coil.compose.AsyncImage
import com.rhea.composefortv.R
import com.rhea.composefortv.data.models.Movie

@Composable
fun MovieCard(
  movie: Movie,
  modifier: Modifier = Modifier,
  onClick: (Movie) -> Unit = {}
) {
  Card(
    onClick = { onClick(movie) },
    modifier = modifier
      .widthIn(max = 320.dp)
      .aspectRatio(16f / 9f),
  ) {
    AsyncImage(
      model = movie.posterPath,
      contentDescription = movie.overview,
      contentScale = ContentScale.Crop,
      placeholder = painterResource(id = R.drawable.placeholder)
    )
  }
}
