package com.example.demoapp.presentation.team

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.model.ApiError
import com.example.demoapp.domain.model.Team
import com.example.demoapp.domain.usercase.GetTeamUseCase
import com.example.demoapp.domain.usercase.base.UseCaseResponse
import kotlinx.coroutines.cancel

class TeamViewModel constructor(private val getTeamsUseCase: GetTeamUseCase) : ViewModel() {

    val teamsData = MutableLiveData<List<Team>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getTeams() {
        showProgressbar.value = true
        getTeamsUseCase.invoke(
            viewModelScope, null,
            object : UseCaseResponse<List<Team>> {
                override fun onSuccess(result: List<Team>) {
                    Log.i(TAG, "result: $result")
                    teamsData.value = result
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
        private val TAG = TeamViewModel::class.java.name
    }

}