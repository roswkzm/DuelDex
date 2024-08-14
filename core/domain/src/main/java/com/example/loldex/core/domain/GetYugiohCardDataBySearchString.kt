package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.YugiohRepository
import javax.inject.Inject

class GetYugiohCardDataBySearchString @Inject constructor(
    private val yugiohRepository: YugiohRepository
) {
    operator fun invoke(
        searchString: String
    ) = yugiohRepository.getYugiohCardDataBySearchString(searchString)
}