package com.example.demoapp.domain.usercase

import com.example.demoapp.domain.model.Matches
import com.example.demoapp.domain.repository.MatchRepository
import com.example.demoapp.domain.usercase.base.UseCase

class GetMatchUseCase constructor(
    private val matchRepository: MatchRepository
) : UseCase<Matches, Any?>() {

    override suspend fun run(params: Any?): Matches {
        return matchRepository.getMatches()
    }

}