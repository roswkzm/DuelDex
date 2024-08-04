package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.DisneyRepository
import javax.inject.Inject

class GetCharacterByNameUseCase @Inject constructor(
    private val disneyRepository: DisneyRepository,
) {
    operator fun invoke(
        name: String,
    ) = disneyRepository.getCharacterByName(name)
}