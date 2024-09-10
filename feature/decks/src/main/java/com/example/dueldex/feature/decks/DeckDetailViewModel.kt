package com.example.dueldex.feature.decks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dueldex.core.data.repository.DecksRepository
import com.example.dueldex.core.model.DeckData
import com.example.dueldex.core.model.DeckWithCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckDetailViewModel @Inject constructor(
    private val decksRepository: DecksRepository
) : ViewModel() {

    private val _deckDetailUiState = MutableStateFlow<DeckDetailUiState>(DeckDetailUiState.Loading)
    val deckDetailUiState: StateFlow<DeckDetailUiState> = _deckDetailUiState.asStateFlow()


    fun getDeckWithCards(deckId: Long) {
        _deckDetailUiState.value = DeckDetailUiState.Loading
        viewModelScope.launch {
            try {
                decksRepository.getDeckWithCards(deckId).collect { deckWithCards ->
                    _deckDetailUiState.value = DeckDetailUiState.Success(deckWithCards)
                }
            } catch (e: Exception) {
                _deckDetailUiState.value = DeckDetailUiState.Error(e)
            }
        }
    }

    fun updateDeckName(deckData: DeckData) {
        viewModelScope.launch {
            decksRepository.updateDeckName(deckData)
        }
    }

    fun deleteDeckCard(
        deckId: Long,
        cardId: Long
    ) {
        viewModelScope.launch {
            decksRepository.deleteDeckCard(deckId, cardId)
        }
    }
}

sealed interface DeckDetailUiState {
    data object Loading : DeckDetailUiState
    data class Error(val exception: Exception) : DeckDetailUiState
    data class Success(val deckWithCardData: DeckWithCardData) : DeckDetailUiState
}