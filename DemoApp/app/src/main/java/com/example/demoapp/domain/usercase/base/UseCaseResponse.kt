package com.example.demoapp.domain.usercase.base

import com.example.demoapp.domain.model.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}