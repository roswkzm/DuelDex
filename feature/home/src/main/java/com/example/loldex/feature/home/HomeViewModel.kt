package com.example.loldex.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.loldex.core.common.result.Result
import com.example.loldex.core.common.result.asResult
import com.example.loldex.core.domain.GetYugiohCardDataBySearchString
import com.example.loldex.core.domain.GetYugiohListUseCase
import com.example.loldex.core.model.YugiohCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getYugiohListUseCase: GetYugiohListUseCase,
    private val getYugiohCardDataBySearchString: GetYugiohCardDataBySearchString,
) : ViewModel() {

    private val _yugiohListState: MutableStateFlow<PagingData<YugiohCardData>> =
        MutableStateFlow(value = PagingData.empty())
    val yugiohListState: StateFlow<PagingData<YugiohCardData>> = _yugiohListState

    private val _cardSearchResult: MutableStateFlow<SearchResultUiState> =
        MutableStateFlow(SearchResultUiState.Idle)
    val cardSearchResult: StateFlow<SearchResultUiState> = _cardSearchResult

    init {
        viewModelScope.launch {
            getYugiohListUseCase.invoke(
                num = 10
            ).cachedIn(viewModelScope)
                .collect {
                    _yugiohListState.value = it
                }
        }
    }

    fun cardSearchToQuery(searchQuery: String) {
        viewModelScope.launch {
            getYugiohCardDataBySearchString.invoke(
                searchString = searchQuery,
                onError = {
                    _cardSearchResult.value = SearchResultUiState.Error(it)
                }
            ).asResult()
                .map { searchResult ->
                    when (searchResult) {
                        Result.Loading -> SearchResultUiState.Loading
                        is Result.Error -> SearchResultUiState.Error(searchResult.exception.message ?: "Know Error")
                        is Result.Success -> SearchResultUiState.Success(searchResult.data)
                    }
                }
                .collect {
                    _cardSearchResult.value = it
                }
        }
    }

    fun cardSearchStateIdle() {
        _cardSearchResult.value = SearchResultUiState.Idle
    }
}

sealed interface SearchResultUiState {
    data object Idle : SearchResultUiState
    data object Loading : SearchResultUiState
    data class Error(val errorMessage: String) : SearchResultUiState
    data class Success(val yugiohCardList: List<YugiohCardData>) : SearchResultUiState
}