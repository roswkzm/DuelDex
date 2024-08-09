package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.YugiohRepository
import javax.inject.Inject

class GetYugiohCardDataByIdUseCase @Inject constructor(
    private val yugiohRepository: YugiohRepository
) {
    operator fun invoke(
        id: Long
    ) = yugiohRepository.getYugiohCardDataById(id)
}