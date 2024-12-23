package com.ipa.dev.gamethrones.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.R
import com.ipa.dev.gamethrones.data.listOfCharacters
import com.ipa.dev.gamethrones.ui.screens.home.ScreenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    character: CharacterModel,
    onBackClick: () -> Unit
) {
    ScreenTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(character.fullName) },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                AsyncImage(
                    model = character.imageUrl,
                    contentDescription = character.fullName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                )
                Text(
                    text = character.fullName,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = character.title,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = character.family,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreen_Preview() {
    val character = listOfCharacters[0]
    DetailScreen(
        character = character,
        onBackClick = {}
    )
}