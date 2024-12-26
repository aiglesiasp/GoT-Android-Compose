package com.ipa.dev.gamethrones.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipa.dev.gamethrones.data.listOfCharacters
import com.ipa.dev.gamethrones.ui.screens.detail.DetailScreen
import com.ipa.dev.gamethrones.ui.screens.detail.DetailViewModel
import com.ipa.dev.gamethrones.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(onClick = { character ->
                navController.navigate("detail/${character.id}")
            })
        }
        composable(
            route = "detail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            val characterId = requireNotNull(navBackStackEntry.arguments?.getInt("characterId"))
            DetailScreen(
                vm = viewModel { DetailViewModel(characterId) }, //se crea asi para solo tener 1 instancia
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}