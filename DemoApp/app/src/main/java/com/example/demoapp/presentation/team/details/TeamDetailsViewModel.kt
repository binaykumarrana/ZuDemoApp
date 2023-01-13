package com.example.demoapp.presentation.team.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.model.ApiError
import com.example.demoapp.domain.model.Matches
import com.example.demoapp.domain.usercase.GetTeamDetailsUseCase
import com.example.demoapp.domain.usercase.base.UseCaseResponse
import com.example.demoapp.presentation.match.MatchViewModel
import kotlinx.coroutines.cancel

class TeamDetailsViewModel constructor(private val getMatchesUseCase: GetTeamDetailsUseCase) : ViewModel() {

    val matchesData = MutableLiveData<Matches>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getTeamDetails(teamId: String) {
        showProgressbar.value = true
        getMatchesUseCase.invoke(
            viewModelScope, teamId,
            object : UseCaseResponse<Matches> {
                override fun onSuccess(result: Matches) {
                    Log.i(TAG, "result: $result")
                    matchesData.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }
            },
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = MatchViewModel::class.java.name
    }

}