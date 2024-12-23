package com.ipa.dev.gamethrones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ipa.dev.gamethrones.ui.screens.detail.DetailScreen
import com.ipa.dev.gamethrones.ui.screens.home.HomeScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
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
                ) {
                    DetailScreen()
                }
            }

        }
    }
}


