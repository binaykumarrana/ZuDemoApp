package com.example.demoapp.domain.usercase

import com.example.demoapp.domain.model.Team
import com.example.demoapp.domain.repository.TeamRepository
import com.example.demoapp.domain.usercase.base.UseCase

class GetTeamUseCase constructor(
    private val teamRepository: TeamRepository
) : UseCase<List<Team>, Any?>() {

    override suspend fun run(params: Any?): List<Team> {
        return teamRepository.getTeams()
    }

}