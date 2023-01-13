package com.example.demoapp.domain.usercase

import com.example.demoapp.domain.model.Matches
import com.example.demoapp.domain.repository.TeamDetailsRepository
import com.example.demoapp.domain.usercase.base.UseCase

class GetTeamDetailsUseCase constructor(
    private val teamDetailsRepository: TeamDetailsRepository
) : UseCase<Matches, Any?>() {

    override suspend fun run(params: Any?): Matches {
        return teamDetailsRepository.getTeamDetails(params as String)
    }

}