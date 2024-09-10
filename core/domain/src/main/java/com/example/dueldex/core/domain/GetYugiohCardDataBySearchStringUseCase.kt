package com.example.dueldex.core.domain

import com.example.dueldex.core.data.repository.YugiohRepository
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
        level: Int?,
        onError: (String) -> Unit,
    ) = yugiohRepository.getYugiohCardDataBySearchString(
        searchString = searchString,
        type = type,
        attribute = attribute,
        race = race,
        effect = effect,
        level = level,
        onError = onError,
    )
}