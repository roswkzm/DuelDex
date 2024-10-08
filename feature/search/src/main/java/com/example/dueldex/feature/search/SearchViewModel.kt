package com.example.dueldex.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dueldex.core.common.result.Result
import com.example.dueldex.core.common.result.asResult
import com.example.dueldex.core.data.repository.UserDataRepository
import com.example.dueldex.core.domain.GetYugiohCardDataBySearchStringUseCase
import com.example.dueldex.core.model.YugiohCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getYugiohCardDataBySearchStringUseCase: GetYugiohCardDataBySearchStringUseCase,
    private val userDataRepository: UserDataRepository,
) : ViewModel() {

    private val _cardSearchResult: MutableStateFlow<SearchResultUiState> =
        MutableStateFlow(SearchResultUiState.Idle)
    val cardSearchResult: StateFlow<SearchResultUiState> = _cardSearchResult

    fun cardSearchToQuery(
        searchQuery: String,
        type: String? = null,
        attribute: String? = null,
        race: String? = null,
        effect: String? = null,
        level: Int? = null,
    ) {
        viewModelScope.launch {
            getYugiohCardDataBySearchStringUseCase.invoke(
                searchString = searchQuery,
                type = type,
                attribute = attribute,
                race = race,
                effect = effect,
                level = level,
                onError = {
                    _cardSearchResult.value = SearchResultUiState.Error(it)
                }
            ).asResult()
                .map { searchResult ->
                    when (searchResult) {
                        Result.Loading -> SearchResultUiState.Loading
                        is Result.Error -> SearchResultUiState.Error(
                            searchResult.exception.message ?: "Know Error"
                        )

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

    val recentSearchList = userDataRepository.recentSearchList

    fun addRecentSearch(searchText: String) {
        viewModelScope.launch {
            userDataRepository.addRecentSearch(searchText)
        }
    }

    fun removeRecentSearch(searchText: String) {
        viewModelScope.launch {
            userDataRepository.removeRecentSearch(searchText)
        }
    }

    fun clearAllRecentSearches() {
        viewModelScope.launch {
            userDataRepository.clearAllRecentSearches()
        }
    }
}

sealed interface SearchResultUiState {
    data object Idle : SearchResultUiState
    data object Loading : SearchResultUiState
    data class Error(val errorMessage: String) : SearchResultUiState
    data class Success(val yugiohCardList: List<YugiohCardData>) : SearchResultUiState
}