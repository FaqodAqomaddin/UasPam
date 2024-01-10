package com.example.uaspam.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uaspam.ui.Add.AddScreen
import com.example.uaspam.ui.Add.DestinasiEntry
import com.example.uaspam.ui.Detail.DetailMotorDestination
import com.example.uaspam.ui.Detail.DetailScreen
import com.example.uaspam.ui.Home.DestinasiHomeMotor
import com.example.uaspam.ui.Home.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiHomeMotor.route,
        modifier = Modifier
    ) {
        composable(DestinasiHomeMotor.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailMotorDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
        }
        composable(DestinasiEntry.route) {
            AddScreen(navigateBack = { navController.popBackStack() })
        }
        composable(route = DetailMotorDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailMotorDestination.MotorId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val kontakId = backStackEntry.arguments?.getString(DetailMotorDestination.MotorId)
            kontakId?.let {
                DetailScreen(
                    navigateToEditItem = {},
                    navigateBack = { navController.popBackStack() })
            }
        }
    }
}