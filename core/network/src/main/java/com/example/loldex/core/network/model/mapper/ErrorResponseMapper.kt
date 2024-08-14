package com.example.loldex.core.network.model.mapper

import com.example.loldex.core.network.model.ErrorResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mappers.ApiErrorModelMapper
import com.skydoves.sandwich.retrofit.apiMessage
import com.skydoves.sandwich.retrofit.statusCode

object ErrorResponseMapper : ApiErrorModelMapper<ErrorResponse> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error): ErrorResponse {
        return apiErrorResponse.apiMessage?.let {
            ErrorResponse(
                code = apiErrorResponse.statusCode.code,
                error = it
            )
        }
            ?: ErrorResponse(code = -1, error = "Know Error")
    }
}