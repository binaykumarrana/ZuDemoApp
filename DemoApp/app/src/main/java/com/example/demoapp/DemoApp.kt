package com.example.demoapp

import android.app.Application
import androidx.multidex.MultiDex
import com.example.demoapp.di.MatchModule
import com.example.demoapp.di.NetworkModule
import com.example.demoapp.di.TeamDetailsModule
import com.example.demoapp.di.TeamModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DemoApp)
            modules(listOf(TeamModule, MatchModule, TeamDetailsModule, NetworkModule))
        }

    }
}