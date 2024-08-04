package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.DisneyRepository
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val disneyRepository: DisneyRepository,
) {
    operator fun invoke(
        id: Int,
    ) = disneyRepository.getCharacterById(id)
}