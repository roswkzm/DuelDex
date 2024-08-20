package com.example.loldex.feature.decks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loldex.core.data.repository.DecksRepository
import com.example.loldex.core.model.DeckData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DecksViewModel @Inject constructor(
    private val decksRepository: DecksRepository
) : ViewModel() {

    val allDecks = decksRepository.allDecks.map {
        DecksUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = DecksUiState.Loading,
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
}

sealed interface DecksUiState {
    data object Loading : DecksUiState
    data class Success(val deckList: List<DeckData>) : DecksUiState
}