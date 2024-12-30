package com.ipa.dev.gamethrones.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val id: Int,
    private val repository: CharactersRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()


    data class UiState(
        val isLoading: Boolean = false,
        val character: CharacterModel? = null
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            repository.getCharacter(id).collect { character ->
                _state.value = UiState(isLoading = false, character = character)
            }
        }
    }
}