package com.example.demoapp.data.repository

import com.example.demoapp.data.source.RemoteApiService
import com.example.demoapp.domain.model.Matches
import com.example.demoapp.domain.repository.MatchRepository

class MatchRepositoryImpl(private val apiService: RemoteApiService) : MatchRepository {
    override suspend fun getMatches(): Matches {
        return apiService.getMatches().matches
    }
}