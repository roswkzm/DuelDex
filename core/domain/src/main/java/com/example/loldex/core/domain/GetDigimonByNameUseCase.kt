package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.DigimonRepository
import javax.inject.Inject

class GetDigimonByNameUseCase @Inject constructor(
    private val digimonRepository: DigimonRepository
) {
    operator fun invoke(
        name: String
    ) = digimonRepository.getDigimonByName(name)
}