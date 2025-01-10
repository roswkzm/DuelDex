package com.example.dueldex.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dueldex.core.data.repository.DecksRepository
import com.example.dueldex.core.model.DeckData
import com.example.dueldex.core.model.YugiohCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedCardToDeckViewModel @Inject constructor(
    private val decksRepository: DecksRepository
) : ViewModel() {

    /*
    SharedFlow -> Channel 변경 예정
    Link -> https://medium.com/prnd/viewmodel%EC%97%90%EC%84%9C-%EB%8D%94%EC%9D%B4%EC%83%81-eventflow%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%98%EC%A7%80-%EB%A7%88%EC%84%B8%EC%9A%94-3974e8ddffed
     */
    private val _cardSaveResultFlow = MutableSharedFlow<Boolean>()
    val cardSaveResultFlow: SharedFlow<Boolean> = _cardSaveResultFlow.asSharedFlow()

    val allDecks = decksRepository.allDecks.map {
        SavedDecksUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = SavedDecksUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )

    fun insertDeck(deckName: String) {
        viewModelScope.launch {
            decksRepository.insertDeck(deckName)
        }
    }

    fun deleteDeck(deckData: DeckData) {
        viewModelScope.launch {
            decksRepository.deleteDeck(deckData)
        }
    }

    fun insertDeckCard(
        deckId: Long,
        yugiohCardData: YugiohCardData
    ) {
        viewModelScope.launch {
            try {
                decksRepository.insertDeckCard(deckId, yugiohCardData)
                _cardSaveResultFlow.emit(true)
            } catch (e: Exception) {
                _cardSaveResultFlow.emit(false)
            }
        }
    }
}

sealed interface SavedDecksUiState {
    data object Loading : SavedDecksUiState
    data class Success(val deckList: List<DeckData>) : SavedDecksUiState
}