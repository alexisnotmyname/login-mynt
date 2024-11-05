

package com.alexc.ph.loginmynt.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
}

@Composable
fun rememberMyAppState(
    navController: NavHostController = rememberNavController(),
) = remember(navController) {
    MyAppState(navController)
}

class MyAppState(
    val navController: NavHostController,
) {
    fun navigateToHome() {
        navController.navigate(Screen.Home.route) {
            popUpTo(Screen.Login.route) {
                inclusive = true
            }
        }
    }

    fun navigateBack() {
        navController.navigate(Screen.Login.route) {
            popUpTo(Screen.Home.route) {
                inclusive = true
            }
        }
    }
}