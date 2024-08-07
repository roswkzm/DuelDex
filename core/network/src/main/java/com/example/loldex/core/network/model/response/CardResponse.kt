package com.example.loldex.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardResponse(
    val id: Long,
    val name: String,
    val type: String, // 예시: "Effect Monster", "Spell Card", "Trap Card"
    val frameType: String, // 예시: "effect", "spell", "trap"
    val desc: String,
    val atk: Int? = null, // nullable: 일부 카드 (예: Spell/Trap Card)는 공격력을 갖지 않습니다.
    val def: Int? = null, // nullable: 일부 카드 (예: Spell/Trap Card)는 수비력을 갖지 않습니다.
    val level: Int? = null, // nullable: 일부 카드 (예: Spell/Trap Card)는 레벨을 갖지 않습니다.
    val race: String, // 예시: "Fiend", "Spellcaster", "Dragon"
    val attribute: String? = null, // nullable: 일부 카드 (예: Spell/Trap Card)는 속성을 갖지 않습니다.
    val archetype: String? = null, // nullable: 모든 카드가 Archetype에 속하는 것은 아닙니다.
    @SerialName("ygoprodeck_url")
    val ygoprodeckUrl: String,
    @SerialName("card_images")
    val cardImages: List<CardImageResponse>,
    @SerialName("card_prices")
    val cardPrices: List<CardPriceResponse>
)