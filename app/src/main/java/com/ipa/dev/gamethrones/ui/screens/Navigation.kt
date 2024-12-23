package com.ipa.dev.gamethrones.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipa.dev.gamethrones.data.listOfCharacters
import com.ipa.dev.gamethrones.ui.screens.detail.DetailScreen
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
            val characterId = navBackStackEntry.arguments?.getInt("characterId")
            DetailScreen(
                character = listOfCharacters.first { it.id == characterId },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}