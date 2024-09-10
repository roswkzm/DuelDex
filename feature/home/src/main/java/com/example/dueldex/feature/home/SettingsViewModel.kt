package com.example.dueldex.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dueldex.core.data.repository.UserDataRepository
import com.example.dueldex.core.model.UserEnvData
import com.example.dueldex.core.model.enums.LocalizationConfig
import com.example.dueldex.core.model.enums.ThemeConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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

    fun setThemeConfig(themeConfig: ThemeConfig) {
        viewModelScope.launch {
            userDataRepository.setThemeConfig(themeConfig)
        }
    }

    fun setLocalizationConfig(localizationConfig: LocalizationConfig) {
        viewModelScope.launch {
            userDataRepository.setLocalizationConfig(localizationConfig)
        }
    }
}

sealed interface SettingsUiState {
    data object Loading : SettingsUiState
    data class Success(val appEnvData: UserEnvData) : SettingsUiState
}