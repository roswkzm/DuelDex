package com.example.loldex.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.loldex.core.domain.GetDigimonListUseCase
import com.example.loldex.core.model.DigimonContentData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDigimonListUseCase: GetDigimonListUseCase
) : ViewModel() {

    private val _digimonListState: MutableStateFlow<PagingData<DigimonContentData>> =
        MutableStateFlow(value = PagingData.empty())
    val digimonListState: StateFlow<PagingData<DigimonContentData>> = _digimonListState

    init {
        viewModelScope.launch {
            getDigimonListUseCase.invoke(
                pageSize = 10
            ).cachedIn(viewModelScope)
                .collect {
                    _digimonListState.value = it
                }
        }
    }
}