package com.ipa.dev.gamethrones.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.ui.commonViews.LoadingProgressIndicator
import com.ipa.dev.gamethrones.ui.theme.GameThronesTheme

@Composable
fun ScreenTheme(content: @Composable () -> Unit) {
    GameThronesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen(
    vm: HomeViewModel,
    onClick: (CharacterModel) -> Unit
) {

    val homeState = rememberHomeState()

    homeState.onUiReadyEffect { vm.onUiReady() }

    ScreenTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("GameThrones") },
                    scrollBehavior = homeState.scrollBehavior
                )
            },
            modifier = Modifier.nestedScroll(homeState.scrollBehavior.nestedScrollConnection)
        ) { padding ->

            val state by vm.state.collectAsState()

            if (state.isLoading) {
                LoadingProgressIndicator(modifier = Modifier.padding(padding))
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(horizontal = 4.dp),
                contentPadding = padding
            ) {
                items(state.characters) { character ->
                    CharacterItem(
                        character = character,
                        onClick = { onClick(character) }
                    )
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterModel,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.fullName,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
                .clip(MaterialTheme.shapes.small)
        )
        Text(
            text = character.fullName,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            modifier = Modifier.padding(8.dp)
        )
    }
}


