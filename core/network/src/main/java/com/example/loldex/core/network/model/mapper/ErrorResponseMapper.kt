package com.example.loldex.core.network.model.mapper

import com.example.loldex.core.network.model.ErrorResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.retrofit.apiMessage

object ErrorResponseMapper : ApiErrorModelMapper<ErrorResponse> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error): ErrorResponse {
        return apiErrorResponse.apiMessage?.let { ErrorResponse(error = it) }
            ?: ErrorResponse(error = "Know Error")
    }
}