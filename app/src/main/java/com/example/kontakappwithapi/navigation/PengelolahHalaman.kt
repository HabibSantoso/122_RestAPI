package com.example.kontakappwithapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kontakappwithapi.ui.details.screen.DetailsDestination
import com.example.kontakappwithapi.ui.details.screen.DetailsScreen
import com.example.kontakappwithapi.ui.edit.screen.EditDestination
import com.example.kontakappwithapi.ui.edit.screen.ItemEditScreen
import com.example.kontakappwithapi.ui.home.screen.DestinasiHome
import com.example.kontakappwithapi.ui.home.screen.HomeScreen
import com.example.kontakappwithapi.ui.kontak.screen.DestinasiEntry
import com.example.kontakappwithapi.ui.kontak.screen.EntryKontakScreen


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
                onDetailClick = {itemId ->
                    navController.navigate("${DetailsDestination.route}/$itemId")
                    println(itemId)
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
        composable(DetailsDestination.routeWithArg,
            arguments = listOf(navArgument(DetailsDestination.kontakId){
                type = NavType.IntType
            })
        ){
            backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(DetailsDestination.kontakId)
            itemId?.let {
                DetailsScreen(
                    onEditClick = {itemId ->
                        navController.navigate("${EditDestination.route}/$itemId")
                        println(itemId)
                    },
                    navigateBack = { navController.navigateUp() })
            }
        }
        composable(
            EditDestination.routeWithArg,
            arguments = listOf(navArgument(DetailsDestination.kontakId){
                type = NavType.IntType
            })
        ){
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = {
                    navController.navigate(DestinasiHome.route){
                        popUpTo(DestinasiHome.route){
                            inclusive = true
                        }
                    }
                })
        }
    }
}