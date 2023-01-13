package com.example.demoapp.data.source

import com.example.demoapp.domain.model.MatchResponse
import com.example.demoapp.domain.model.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApiService {

    @GET("/teams")
    suspend fun getTeams(): TeamResponse

    @GET("/teams/matches")
    suspend fun getMatches(): MatchResponse

    @GET("/teams/{id}/matches")
    suspend fun getTeamDetails(@Path("id") id: String): MatchResponse
}