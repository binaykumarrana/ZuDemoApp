package com.example.demoapp.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchResponse(val matches: Matches)

@JsonClass(generateAdapter = true)
data class Matches(
    val previous: List<Match>,
    val upcoming: List<Match>
)

@JsonClass(generateAdapter = true)
data class Match(
    val date: String,
    val description: String,
    val home: String,
    val away: String,
    var winner: String? = null,
    var highlights: String? = null
)