package com.ipa.dev.gamethrones.ui.screens.home

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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ipa.dev.gamethrones.CharacterModel
import com.ipa.dev.gamethrones.listOfCharacters
import com.ipa.dev.gamethrones.ui.theme.GameThronesTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreen() {
    GameThronesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("GameThrones") },
                        scrollBehavior = scrollBehavior
                    )
                },
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            ) { padding ->
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(horizontal = 4.dp),
                    contentPadding = padding
                ) {
                    items(listOfCharacters) { character ->
                        CharacterItem(character = character)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterModel,
) {
    Column {
        AsyncImage(
            model = character.imageUrl,
            contentDescription = character.fullName,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2/3f)
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

@Preview
@Composable
fun CharacterItemPreview() {
    HomeScreen()
}
