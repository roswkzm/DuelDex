package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.YugiohRepository
import javax.inject.Inject

class GetYugiohCardDataByNameUseCase @Inject constructor(
    private val yugiohRepository: YugiohRepository
) {
    operator fun invoke(
        cardName: String
    ) = yugiohRepository.getYugiohCardDataByName(cardName)
}