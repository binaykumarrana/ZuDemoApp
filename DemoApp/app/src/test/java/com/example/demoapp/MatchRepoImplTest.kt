package com.example.demoapp

import com.example.demoapp.data.repository.MatchRepositoryImpl
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

class MatchRepoImplTest {
    @MockK
    lateinit var matchRepositoryImpl: MatchRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getMatchData() = runBlocking {
        val matches = mockk<Matches>()
        every { runBlocking { matchRepositoryImpl.getMatches() } } returns (matches)

        val result = matchRepositoryImpl.getMatches()
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$matches] must be matches on each other!",
            result,
            CoreMatchers.`is`(matches)
        )
    }

    @Test
    fun getPreviousMatchData() = runBlocking {
        val matches = mockk<List<Match>>()
        every { runBlocking { matchRepositoryImpl.getMatches().previous } } returns (matches)

        val result = matchRepositoryImpl.getMatches().previous
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$matches] must be matches on each other!",
            result,
            CoreMatchers.`is`(matches)
        )
    }

    @Test
    fun getUpcomingMatchData() = runBlocking {
        val matches = mockk<List<Match>>()
        every { runBlocking { matchRepositoryImpl.getMatches().upcoming } } returns (matches)

        val result = matchRepositoryImpl.getMatches().upcoming
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$matches] must be matches on each other!",
            result,
            CoreMatchers.`is`(matches)
        )
    }
}