package com.example.demoapp.domain.repository

import com.example.demoapp.domain.model.Matches

interface MatchRepository {
    suspend fun getMatches(): Matches
}