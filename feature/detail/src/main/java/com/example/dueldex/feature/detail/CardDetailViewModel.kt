package com.example.dueldex.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dueldex.core.common.result.Result
import com.example.dueldex.core.common.result.asResult
import com.example.dueldex.core.domain.GetYugiohCardDataByNameUseCase
import com.example.dueldex.core.model.YugiohCardData
import com.example.dueldex.feature.detail.navigation.DetailArgs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getYugiohCardDataByNameUseCase: GetYugiohCardDataByNameUseCase,
) : ViewModel() {

    private val detailArgs: DetailArgs = DetailArgs(savedStateHandle)
    private val cardName = detailArgs.cardName

    private val _cardDetailUiState = MutableStateFlow<CardDetailUiState>(CardDetailUiState.Loading)
    val cardDetailUiState: StateFlow<CardDetailUiState> = _cardDetailUiState.asStateFlow()

    init {
        loadCardDetailData()
    }

    fun retryLoadCardDetailData() {
        _cardDetailUiState.value = CardDetailUiState.Loading
        loadCardDetailData()
    }

    private fun loadCardDetailData() {
        viewModelScope.launch {
            getYugiohCardDataByNameUseCase.invoke(
                cardName = cardName
            ).asResult()
                .map { yugiohCardDataResult ->
                    when (yugiohCardDataResult) {
                        is Result.Success -> CardDetailUiState.Success(yugiohCardDataResult.data)
                        is Result.Error -> CardDetailUiState.Error
                        Result.Loading -> CardDetailUiState.Loading
                    }
                }
                .collect {
                    _cardDetailUiState.value = it
                }
        }
    }
}

sealed interface CardDetailUiState {
    data object Loading : CardDetailUiState
    data object Error : CardDetailUiState
    data class Success(val yugiohCardDat: YugiohCardData) : CardDetailUiState
}