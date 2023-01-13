package com.example.demoapp.presentation.match

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.model.ApiError
import com.example.demoapp.domain.model.Matches
import com.example.demoapp.domain.usercase.GetMatchUseCase
import com.example.demoapp.domain.usercase.base.UseCaseResponse
import kotlinx.coroutines.cancel

class MatchViewModel constructor(private val getMatchesUseCase: GetMatchUseCase) : ViewModel() {

    val matchesData = MutableLiveData<Matches>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getMatches() {
        showProgressbar.value = true
        getMatchesUseCase.invoke(
            viewModelScope, null,
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