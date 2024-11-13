package com.rhea.composefortv.ui.common

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import kotlinx.coroutines.delay

@Composable
fun LoadingScreen(
  modifier: Modifier = Modifier,
  circleSize: Dp = 25.dp,
  circleColor: Color = MaterialTheme.colorScheme.secondary,
  spaceBetween: Dp = 10.dp,
  travelDistance: Dp = 20.dp
) {
  val circles = listOf(
    remember { Animatable(0f) },
    remember { Animatable(0f) },
    remember { Animatable(0f) }
  )

  circles.forEachIndexed { index, animatable ->
    LaunchedEffect(key1 = animatable) {
      delay(index * 100L)
      animatable.animateTo(
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
          animation = keyframes {
            durationMillis = 1200
            0.0f at 0 using LinearOutSlowInEasing
            1.0f at 300 using LinearOutSlowInEasing
            0.0f at 600 using LinearOutSlowInEasing
            0.0f at 1200 using LinearOutSlowInEasing
          },
          repeatMode = RepeatMode.Reverse
        )
      )
    }
  }

  val distance = with(LocalDensity.current) { travelDistance.toPx() }

  Row(
    modifier = modifier.fillMaxSize(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(spaceBetween, Alignment.CenterHorizontally)
  ) {
    circles.forEach {
      Box(
        modifier = Modifier
          .size(circleSize)
          .graphicsLayer {
            translationY = -it.value * distance
          }
          .background(
            color = circleColor,
            shape = CircleShape
          )
      )
    }
  }

}