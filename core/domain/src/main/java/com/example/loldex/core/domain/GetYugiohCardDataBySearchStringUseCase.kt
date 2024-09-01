package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.YugiohRepository
import javax.inject.Inject

class GetYugiohCardDataBySearchStringUseCase @Inject constructor(
    private val yugiohRepository: YugiohRepository
) {
    operator fun invoke(
        searchString: String,
        type: String?,
        attribute: String?,
        race: String?,
        effect: String?,
        onError: (String) -> Unit,
    ) = yugiohRepository.getYugiohCardDataBySearchString(
        searchString = searchString,
        type = type,
        attribute = attribute,
        race = race,
        effect = effect,
        onError = onError,
    )
}