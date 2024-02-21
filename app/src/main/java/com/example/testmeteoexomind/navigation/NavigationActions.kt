package com.example.testmeteoexomind.navigation

import androidx.navigation.NavHostController

class NavigationActions(private val navigationController: NavHostController) {

    fun navigatToMeteoScreen() {
        navigationController.navigate(
            Screen.METEO_SCREEN
        )
    }

    fun navigatToBack() {
        navigationController.popBackStack()
    }

}

object Screen {
    const val PRINCIPAL_SCREEN = "principal"
    const val METEO_SCREEN = "meteo"
}