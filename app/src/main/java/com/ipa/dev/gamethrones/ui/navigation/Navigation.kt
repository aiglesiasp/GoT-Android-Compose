package com.ipa.dev.gamethrones.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ipa.dev.gamethrones.ui.screens.detail.DetailScreen
import com.ipa.dev.gamethrones.ui.screens.detail.DetailViewModel
import com.ipa.dev.gamethrones.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable<Home> {
            HomeScreen(onClick = { character ->
                navController.navigate(Detail(character.id))
            })
        }

        composable<Detail>(
        ) { navBackStackEntry ->
            val detail = navBackStackEntry.toRoute<Detail>()
            DetailScreen(
                vm = viewModel { DetailViewModel(detail.characterId) },
                onBackClick = { navController.popBackStack(route = Home, inclusive = false) },
            )
        }
    }
}