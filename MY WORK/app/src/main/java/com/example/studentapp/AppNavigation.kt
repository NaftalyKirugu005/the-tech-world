package com.example.studentapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val userDao = db.userDao()
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                userDao = userDao,
                onLoginSuccess = {
                    navController.navigate("dashboard") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegistrationScreen(
                userDao = userDao,
                onRegistrationSuccess = {
                    navController.navigate("login")
                },
                onLoginClick = {
                    navController.navigate("login")
                }
            )
        }
        composable("dashboard") {
            DashboardScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                },
                onGoToComponents = {
                    navController.navigate("component_practice")
                },
                onGoToProfile = {
                    navController.navigate("profile")
                },
                onGoToSettings = {
                    navController.navigate("settings")
                }
            )
        }
        composable("profile") {
            ProfileScreen(onBack = { navController.popBackStack() })
        }
        composable("settings") {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
        composable("component_practice") {
            ComponentPracticeScreen(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
