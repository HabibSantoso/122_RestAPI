package com.example.kontakappwithapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.kontakappwithapi.ui.home.screen.DestinasiHome
import com.example.kontakappwithapi.ui.home.screen.HomeScreen
import com.example.kontakappwithapi.ui.kontak.screen.DestinasiEntry
import com.example.kontakappwithapi.ui.kontak.screen.EntryKontakScreen
import androidx.navigation.NavHost as NavHost

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier,
    ) {
        composable(DestinasiHome.route) {
            HomeScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            },
                onDetailClick = {
                })
        }
        composable(DestinasiEntry.route) {
            EntryKontakScreen(navigateBack = {
                navController.navigate(
                    DestinasiHome.route
                ) {
                    popUpTo(DestinasiHome.route) {
                        inclusive = true
                    }
                }
            })
        }
    }
}