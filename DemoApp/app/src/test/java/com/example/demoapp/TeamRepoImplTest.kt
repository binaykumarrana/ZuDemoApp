package com.example.demoapp

import com.example.demoapp.data.repository.TeamRepositoryImpl
import com.example.demoapp.domain.model.Team
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

class TeamRepoImplTest {
    @MockK
    lateinit var teamRepositoryImpl: TeamRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getTeamsData() = runBlocking {
        val teams = mockk<List<Team>>()
        every { runBlocking { teamRepositoryImpl.getTeams() } } returns (teams)

        val result = teamRepositoryImpl.getTeams()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$teams] must be matches on each other!",
            result,
            CoreMatchers.`is`(teams)
        )
    }
}