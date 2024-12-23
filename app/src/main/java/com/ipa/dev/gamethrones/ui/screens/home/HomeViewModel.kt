package com.ipa.dev.gamethrones.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.listOfCharacters
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var uiState by mutableStateOf(UiState())
        private set

    data class UiState(
        val isLoading: Boolean = false,
        val characters: List<CharacterModel> = emptyList()
    )

    fun onUiReady() {
        viewModelScope.launch {
            uiState = UiState(isLoading = true)
            delay(2000)
            uiState = UiState(isLoading = false, characters = listOfCharacters)

        }
    }
}