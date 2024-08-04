package com.example.loldex.core.domain

import com.example.loldex.core.data.repository.DigimonRepository
import javax.inject.Inject

class GetDigimonByIdUseCase @Inject constructor(
    private val digimonRepository: DigimonRepository
) {
    operator fun invoke(
        id: Int
    ) = digimonRepository.getDigimonById(id)
}