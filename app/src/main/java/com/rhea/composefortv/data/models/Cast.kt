package com.rhea.composefortv.data.models

import com.squareup.moshi.Json

data class Cast(
  val adult: Boolean = false,
  @Json(name = "cast_id")
  val castId: Int = 0,
  val character: String = "",
  @Json(name = "credit_id")
  val creditId: String = "",
  val gender: Int = 0,
  val id: Int = 0,
  @Json(name = "known_for_department")
  val knownForDepartment: String = "",
  val name: String = "",
  val order: Int = 0,
  @Json(name = "original_name")
  val originalName: String = "",
  val popularity: Double = 0.0,
  @Json(name = "profile_path")
  val profilePath: String = ""
)

data class CastResponse(
  val cast: List<Cast> = listOf(),
  val id: Int = 0
)