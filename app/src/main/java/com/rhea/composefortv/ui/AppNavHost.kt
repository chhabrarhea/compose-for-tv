package com.rhea.composefortv.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rhea.composefortv.ui.catalog.CatalogBrowser

@Composable
fun AppNavHost(
  navController: NavHostController = rememberNavController()
) {
  NavHost(navController = navController, startDestination = "/") {
    composable("/") {
      CatalogBrowser(
        onMovieSelected = {
          navController.navigate("/movie/${it.id}")
        }
      )
    }
  }
}