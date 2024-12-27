package com.ipa.dev.gamethrones.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ipa.dev.gamethrones.R
import com.ipa.dev.gamethrones.data.CharacterModel
import com.ipa.dev.gamethrones.ui.commonViews.LoadingProgressIndicator
import com.ipa.dev.gamethrones.ui.screens.home.ScreenTheme

@Composable
fun DetailScreen(
    vm: DetailViewModel = viewModel(),
    onBackClick: () -> Unit
) {
    val state by vm.state.collectAsState()

    ScreenTheme {
        Scaffold(
            topBar = { DetailTopBar(state.character?.fullName ?: "", onBackClick) }
        ) { padding ->

            if (state.isLoading) {
                LoadingProgressIndicator(modifier = Modifier.padding(padding))
            }

            state.character?.let { character ->
                DetailScreenContent(character = character, modifier = Modifier.padding(padding))
            }
        }
    }
}

@Composable
private fun DetailScreenContent(
    character: CharacterModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
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

@Preview(showBackground = true)
@Composable
private fun DetailScreen_Preview() {
    DetailScreenContent(
        character = CharacterModel(1, "Leonado Di Caprio", "The best", "Targarian", "")
    )
}