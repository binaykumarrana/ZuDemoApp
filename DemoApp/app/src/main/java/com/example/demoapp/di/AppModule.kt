package com.example.demoapp.di

import com.example.demoapp.presentation.match.MatchViewModel
import com.example.demoapp.presentation.team.TeamViewModel
import com.example.demoapp.presentation.team.details.TeamDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val TeamModule = module {
    viewModel { TeamViewModel(get()) }
    single { createGetTeamUseCase(get()) }
    single { createTeamRepository(get()) }
}

val MatchModule = module {
    viewModel { MatchViewModel(get()) }
    single { createGetMatchUseCase(get()) }
    single { createMatchRepository(get()) }
}

val TeamDetailsModule = module {
    viewModel { TeamDetailsViewModel(get()) }
    single { createGetTeamDetailsUseCase(get()) }
    single { createTeamDetailsRepository(get()) }
}