package com.alexc.ph.loginmynt.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexc.ph.loginmynt.ui.home.HomeScreen
import com.alexc.ph.loginmynt.ui.login.LoginScreen

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    appState: MyAppState = rememberMyAppState()
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        NavHost(
            navController = appState.navController,
            startDestination = Screen.Login.route,
            modifier = Modifier
        ) {
            composable(Screen.Login.route) {
                LoginScreen(navigateToHome = {
                    appState.navigateToHome()
                })
            }
            composable(Screen.Home.route) {
                HomeScreen(logoutClick ={
                    appState.navigateBack()
                })
            }
        }
    }
}