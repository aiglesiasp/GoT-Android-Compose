package com.ipa.dev.gamethrones.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()

    data class UiState(
        val isLoading: Boolean = false,
        val characters: List<CharacterModel> = emptyList()
    )

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(isLoading = false, characters = repository.getCharacters())
        }
    }
}