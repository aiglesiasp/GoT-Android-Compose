package com.ipa.dev.gamethrones.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ipa.dev.gamethrones.data.CharactersRepository
import com.ipa.dev.gamethrones.data.remotedatasource.CharacterRemoteDataSource
import com.ipa.dev.gamethrones.ui.screens.detail.DetailScreen
import com.ipa.dev.gamethrones.ui.screens.detail.DetailViewModel
import com.ipa.dev.gamethrones.ui.screens.home.HomeScreen
import com.ipa.dev.gamethrones.ui.screens.home.HomeViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val characterRemoteDataSource = CharacterRemoteDataSource()
    val charactersRepository = CharactersRepository(remoteDataSource = characterRemoteDataSource)

    NavHost(
        navController = navController,
        startDestination = Home
    ) {

        composable<Home> {
            HomeScreen(
                vm = viewModel { HomeViewModel(repository = charactersRepository) } ,
                onClick = { character -> navController.navigate(Detail(character.id)) }
            )
        }

        composable<Detail>(
        ) { navBackStackEntry ->
            val detail = navBackStackEntry.toRoute<Detail>()
            DetailScreen(
                vm = viewModel { DetailViewModel(id = detail.characterId, repository = charactersRepository) },
                onBackClick = { navController.popBackStack(route = Home, inclusive = false) },
            )
        }
    }
}