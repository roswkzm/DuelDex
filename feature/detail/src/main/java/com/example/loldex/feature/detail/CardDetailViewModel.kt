package com.example.loldex.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loldex.core.common.result.Result
import com.example.loldex.core.common.result.asResult
import com.example.loldex.core.domain.GetYugiohCardDataByIdUseCase
import com.example.loldex.core.model.YugiohCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardDetailViewModel @Inject constructor(
    private val getYugiohCardDataByIdUseCase: GetYugiohCardDataByIdUseCase
) : ViewModel() {

    private val _cardDetailUiState = MutableStateFlow<CardDetailUiState>(CardDetailUiState.Loading)
    val cardDetailUiState: StateFlow<CardDetailUiState> = _cardDetailUiState.asStateFlow()

    fun getYugiohCardDataById(id: Long) {
        viewModelScope.launch {
            getYugiohCardDataByIdUseCase.invoke(
                id = id
            ).asResult()
                .map { yugiohCardDataResult ->
                    when (yugiohCardDataResult) {
                        is Result.Success -> CardDetailUiState.Success(yugiohCardDataResult.data)
                        is Result.Error -> CardDetailUiState.Error
                        Result.Loading -> CardDetailUiState.Loading
                    }
                }
                .collect {

                }
        }
    }
}

sealed interface CardDetailUiState {
    data object Loading : CardDetailUiState
    data object Error : CardDetailUiState
    data class Success(val channelDetailData: YugiohCardData) : CardDetailUiState
}