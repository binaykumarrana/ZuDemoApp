package com.example.demoapp.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamResponse(val teams: List<Team>)

@JsonClass(generateAdapter = true)
data class Team(
    var id: String,
    var name: String,
    var logo: String?
)