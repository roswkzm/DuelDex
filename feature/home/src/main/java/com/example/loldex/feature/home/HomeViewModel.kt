package com.example.loldex.feature.home

import androidx.lifecycle.ViewModel
import com.example.loldex.core.domain.GetDigimonListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getDigimonListUseCase: GetDigimonListUseCase
) : ViewModel() {

}