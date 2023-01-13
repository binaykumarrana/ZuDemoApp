package com.example.demoapp

import com.example.demoapp.di.MatchModule
import com.example.demoapp.di.NetworkModule
import com.example.demoapp.di.TeamDetailsModule
import com.example.demoapp.di.TeamModule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class KoinModuleTest: AutoCloseKoinTest() {

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(listOf(TeamModule, MatchModule, TeamDetailsModule, NetworkModule))
        }.checkModules()
    }
}