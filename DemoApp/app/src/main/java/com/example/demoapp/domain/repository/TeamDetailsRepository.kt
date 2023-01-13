package com.example.demoapp.domain.repository

import com.example.demoapp.domain.model.Matches

interface TeamDetailsRepository {
    suspend fun getTeamDetails(teamId: String): Matches
}