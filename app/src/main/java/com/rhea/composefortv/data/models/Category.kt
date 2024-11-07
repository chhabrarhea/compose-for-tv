package com.rhea.composefortv.data.models

data class Category(
  val movies: List<Movie>,
  val id: Int,
  val title: String
)
