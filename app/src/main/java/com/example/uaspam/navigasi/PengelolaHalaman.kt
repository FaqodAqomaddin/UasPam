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
import com.example.uaspam.ui.Add.AddScreenPemilik
import com.example.uaspam.ui.Add.DestinasiEntry
import com.example.uaspam.ui.Add.DestinasiEntryPemilik
import com.example.uaspam.ui.DestinasiHomePage
import com.example.uaspam.ui.Detail.DetailMotorDestination
import com.example.uaspam.ui.Detail.DetailMotorScreen
import com.example.uaspam.ui.Detail.DetailPemilikDestination
import com.example.uaspam.ui.Detail.DetailPemilikScreen
import com.example.uaspam.ui.Edit.EditMotorDestination
import com.example.uaspam.ui.Edit.EditPemilikDestination
import com.example.uaspam.ui.Edit.EditPemilikScreen
import com.example.uaspam.ui.Edit.EditScreen
import com.example.uaspam.ui.Home.DestinasiHomeMotor
import com.example.uaspam.ui.Home.DestinasiHomePemilik
import com.example.uaspam.ui.Home.HomeScreen
import com.example.uaspam.ui.Home.HomeScreenPemilik
import com.example.uaspam.ui.Homepage

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiHomePage.route,
        modifier = Modifier
    ) {
        composable(DestinasiHomePage.route){
            Homepage(navigateToPemilik = { navController.navigate(DestinasiHomePemilik.route) }, navigateToMotor = {navController.navigate(DestinasiHomeMotor.route) })
        }
        composable(DestinasiHomeMotor.route) {
            HomeScreen(
                navigateBack = {navController.popBackStack()},
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailMotorDestination.route}/$itemId")
                    println("itemId: $itemId")
                })
        }
        composable(DestinasiEntry.route) {
            AddScreen(navigateBack = { navController.popBackStack() })
        }
        composable(
            route = DetailMotorDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailMotorDestination.MotorId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val motorId = backStackEntry.arguments?.getString(DetailMotorDestination.MotorId)
            motorId?.let {
                DetailMotorScreen(
                    navigateToEditItem = {
                        navController.navigate("${EditMotorDestination.route}/$motorId")
                        println("motorId: $motorId")
                    },
                    navigateBack = { navController.popBackStack() })
            }
        }
        composable(
            route = EditMotorDestination.routeWithArgs,
            arguments = listOf(navArgument(EditMotorDestination.MotorId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val motorId = backStackEntry.arguments?.getString(EditMotorDestination.MotorId)
            motorId?.let {
                EditScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
        composable(DestinasiHomePemilik.route) {
            HomeScreenPemilik(
                navigateBack = {navController.popBackStack()},
                navigateToItemEntry = { navController.navigate(DestinasiEntryPemilik.route) },
                onDetailClick = { pemilikId ->
                    navController.navigate("${DetailPemilikDestination.route}/$pemilikId")
                    println("itemId: $pemilikId")
                })
        }
        composable(DestinasiEntryPemilik.route) {
            AddScreenPemilik(navigateBack = { navController.popBackStack() })
        }
        composable(
            route = DetailPemilikDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailPemilikDestination.pemilikId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val pemilikId = backStackEntry.arguments?.getString(DetailPemilikDestination.pemilikId)
            pemilikId?.let {
                DetailPemilikScreen(
                    navigateToEditItem = {
                        navController.navigate("${EditPemilikDestination.route}/$pemilikId")
                        println("pemilikId: $pemilikId")
                    },
                    navigateBack = { navController.popBackStack() })
            }
        }
        composable(
            route = EditPemilikDestination.routeWithArgs,
            arguments = listOf(navArgument(EditPemilikDestination.pemilikId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val pemilikId = backStackEntry.arguments?.getString(EditPemilikDestination.pemilikId)
            pemilikId?.let {
                EditPemilikScreen(
                    navigateBack = { navController.popBackStack() },
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}