package com.example.loldex.feature.decks

import androidx.lifecycle.ViewModel
import com.example.loldex.core.data.repository.DecksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DecksViewModel @Inject constructor(
    private val decksRepository: DecksRepository
) : ViewModel() {


}