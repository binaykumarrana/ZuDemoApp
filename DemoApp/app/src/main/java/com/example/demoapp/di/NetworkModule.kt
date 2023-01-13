package com.example.demoapp.di

import com.example.demoapp.BuildConfig
import com.example.demoapp.data.repository.MatchRepositoryImpl
import com.example.demoapp.data.repository.TeamDetailsRepositoryImpl
import com.example.demoapp.data.repository.TeamRepositoryImpl
import com.example.demoapp.data.source.RemoteApiService
import com.example.demoapp.domain.repository.MatchRepository
import com.example.demoapp.domain.repository.TeamDetailsRepository
import com.example.demoapp.domain.repository.TeamRepository
import com.example.demoapp.domain.usercase.GetMatchUseCase
import com.example.demoapp.domain.usercase.GetTeamDetailsUseCase
import com.example.demoapp.domain.usercase.GetTeamUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): RemoteApiService {
    return retrofit.create(RemoteApiService::class.java)
}

fun createTeamRepository(apiService: RemoteApiService): TeamRepository {
    return TeamRepositoryImpl(apiService)
}

fun createGetTeamUseCase(teamRepository: TeamRepository): GetTeamUseCase {
    return GetTeamUseCase(teamRepository)
}

fun createMatchRepository(apiService: RemoteApiService): MatchRepository {
    return MatchRepositoryImpl(apiService)
}

fun createGetMatchUseCase(matchRepository: MatchRepository): GetMatchUseCase {
    return GetMatchUseCase(matchRepository)
}

fun createTeamDetailsRepository(apiService: RemoteApiService): TeamDetailsRepository {
    return TeamDetailsRepositoryImpl(apiService)
}

fun createGetTeamDetailsUseCase(matchRepository: TeamDetailsRepository): GetTeamDetailsUseCase {
    return GetTeamDetailsUseCase(matchRepository)
}