package com.rhea.composefortv.data.models

data class Movie(
  var id: Long = 0,
  var title: String = "",
  var description: String = "",
  var backgroundImageUrl: String = "",
  var cardImageUrl: String = "",
  var videoUrl: String = "",
  var studio: String = ""
)