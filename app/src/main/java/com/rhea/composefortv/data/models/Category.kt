package com.rhea.composefortv.data.models

import com.squareup.moshi.Json

data class Category(
  val id: Int,
  val title: String,
  val movies: List<Movie>,
)

data class Movies(@Json(name = "list") val list: List<Category>)
