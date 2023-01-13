package com.example.demoapp.data.repository

import com.example.demoapp.data.source.RemoteApiService
import com.example.demoapp.domain.model.Team
import com.example.demoapp.domain.repository.TeamRepository

class TeamRepositoryImpl(private val apiService: RemoteApiService) : TeamRepository {

    override suspend fun getTeams(): List<Team> {
        return apiService.getTeams().teams
    }
}