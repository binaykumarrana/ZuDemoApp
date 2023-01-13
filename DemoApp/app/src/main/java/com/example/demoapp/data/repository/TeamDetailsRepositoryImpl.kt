package com.example.demoapp.data.repository

import com.example.demoapp.data.source.RemoteApiService
import com.example.demoapp.domain.model.Matches
import com.example.demoapp.domain.repository.TeamDetailsRepository

class TeamDetailsRepositoryImpl(private val apiService: RemoteApiService) : TeamDetailsRepository {
    override suspend fun getTeamDetails(teamId: String): Matches {
        return apiService.getTeamDetails(teamId).matches
    }
}