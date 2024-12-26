package com.ipa.dev.gamethrones.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.data.CharactersRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int) : ViewModel() {

    private val repository = CharactersRepository()

    var state by mutableStateOf(UiSate())
        private set

    data class UiSate(
        val isLoading: Boolean = false,
        val character: CharacterModel? = null
    )

    init {
        viewModelScope.launch {
            state = UiSate(isLoading = true)
            state = UiSate(isLoading = false, character = repository.getCharacter(id))

        }
    }


}