package com.example.dueldex.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dueldex.core.domain.GetYugiohListUseCase
import com.example.dueldex.core.model.YugiohCardData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getYugiohListUseCase: GetYugiohListUseCase,
) : ViewModel() {

    private val _yugiohListState: MutableStateFlow<PagingData<YugiohCardData>> =
        MutableStateFlow(value = PagingData.empty())
    val yugiohListState: StateFlow<PagingData<YugiohCardData>> = _yugiohListState

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
}