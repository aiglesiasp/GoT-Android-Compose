package com.ipa.dev.gamethrones.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.CharactersRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int) : ViewModel() {

    private val repository = CharactersRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> get() = _state.asStateFlow()


    data class UiState(
        val isLoading: Boolean = false,
        val character: CharacterModel? = null
    )

    init {
        viewModelScope.launch {
            _state.value = UiState(isLoading = true)
            _state.value = UiState(isLoading = false, character = repository.getCharacter(id))

        }
    }
}