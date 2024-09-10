package com.example.dueldex.core.domain

import com.example.dueldex.core.data.repository.YugiohRepository
import javax.inject.Inject

class GetYugiohCardDataByNameUseCase @Inject constructor(
    private val yugiohRepository: YugiohRepository
) {
    operator fun invoke(
        cardName: String
    ) = yugiohRepository.getYugiohCardDataByName(cardName)
}