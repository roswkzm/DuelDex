package com.example.dueldex.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dueldex.core.data.repository.DecksRepository
import com.example.dueldex.core.model.DeckData
import com.example.dueldex.core.model.YugiohCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedCardToDeckViewModel @Inject constructor(
    private val decksRepository: DecksRepository
) : ViewModel() {

    private val _eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventChannel = _eventChannel.receiveAsFlow()

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
                _eventChannel.send(Event.CardSaveResult(true))
            } catch (e: Exception) {
                _eventChannel.send(Event.CardSaveResult(false))
            }
        }
    }
}

sealed interface SavedDecksUiState {
    data object Loading : SavedDecksUiState
    data class Success(val deckList: List<DeckData>) : SavedDecksUiState
}

sealed class Event {
    data class CardSaveResult(val isSuccess: Boolean) : Event()
}