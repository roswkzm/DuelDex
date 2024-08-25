package com.example.loldex.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loldex.core.data.repository.DecksRepository
import com.example.loldex.core.model.DeckData
import com.example.loldex.core.model.YugiohCardData
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