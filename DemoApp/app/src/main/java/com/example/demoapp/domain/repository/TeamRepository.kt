package com.example.demoapp.domain.repository

import com.example.demoapp.domain.model.Team

interface TeamRepository {
    suspend fun getTeams(): List<Team>
}