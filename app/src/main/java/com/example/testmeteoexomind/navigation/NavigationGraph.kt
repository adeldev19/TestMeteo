package com.example.testmeteoexomind.navigation

import com.example.testmeteoexomind.PrincipalScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testmeteoexomind.meteo.MeteoScreen

@Composable
fun NavigationGraph (
    navigationController: NavHostController = rememberNavController(),
    navigationActions: NavigationActions = remember(navigationController) {
        NavigationActions(navigationController)
    }
) {
    NavHost(
        navController = navigationController,
        startDestination = Screen.PRINCIPAL_SCREEN
    ) {
        composable(
            Screen.PRINCIPAL_SCREEN
        ) {
            PrincipalScreen(onButtonClicked = {
                navigationActions.navigatToMeteoScreen()
            })
        }
        composable(
            Screen.METEO_SCREEN
        ) {
            MeteoScreen(
                navBack = {
                    navigationActions.navigatToBack()
                }
            )
        }
    }
}