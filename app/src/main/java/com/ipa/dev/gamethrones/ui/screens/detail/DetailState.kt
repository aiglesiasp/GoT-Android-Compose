package com.ipa.dev.gamethrones.ui.screens.detail

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

class DetailState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val scrollBehavior: TopAppBarScrollBehavior,
) {

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberDetailState(
    scrollBehavior : TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
) : DetailState {
    return remember(scrollBehavior) { DetailState(scrollBehavior) }
}