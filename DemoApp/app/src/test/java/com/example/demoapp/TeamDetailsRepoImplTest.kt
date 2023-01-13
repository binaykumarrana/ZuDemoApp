package com.example.demoapp

import com.example.demoapp.data.repository.TeamDetailsRepositoryImpl
import com.example.demoapp.domain.model.Match
import com.example.demoapp.domain.model.Matches
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

private const val TEAM_ID = "767ec50c-7fdb-4c3d-98f9-d6727ef8252b"

class TeamDetailsRepoImplTest {
    @MockK
    lateinit var teamDetailsRepositoryImpl: TeamDetailsRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getMatchData() = runBlocking {
        val matches = mockk<Matches>()
        every { runBlocking { teamDetailsRepositoryImpl.getTeamDetails(TEAM_ID) } } returns (matches)

        val result = teamDetailsRepositoryImpl.getTeamDetails(TEAM_ID)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$matches] must be matches on each other!",
            result,
            CoreMatchers.`is`(matches)
        )
    }

    @Test
    fun getPreviousMatchData() = runBlocking {
        val matches = mockk<List<Match>>()
        every { runBlocking { teamDetailsRepositoryImpl.getTeamDetails(TEAM_ID).previous } } returns (matches)

        val result = teamDetailsRepositoryImpl.getTeamDetails(TEAM_ID).previous
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$matches] must be matches on each other!",
            result,
            CoreMatchers.`is`(matches)
        )
    }

    @Test
    fun getUpcomingMatchData() = runBlocking {
        val matches = mockk<List<Match>>()
        every { runBlocking { teamDetailsRepositoryImpl.getTeamDetails(TEAM_ID).upcoming } } returns (matches)

        val result = teamDetailsRepositoryImpl.getTeamDetails(TEAM_ID).upcoming
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$matches] must be matches on each other!",
            result,
            CoreMatchers.`is`(matches)
        )
    }
}