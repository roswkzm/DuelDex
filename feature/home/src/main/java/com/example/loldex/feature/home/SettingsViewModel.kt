package com.example.loldex.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loldex.core.data.repository.UserDataRepository
import com.example.loldex.core.model.UserEnvData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
) : ViewModel() {

    val settingsUiState: StateFlow<SettingsUiState> = userDataRepository.userEnvData.map {
        SettingsUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = SettingsUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val appEnvData: UserEnvData) : SettingsUiState
}