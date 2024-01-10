package com.example.uaspam.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uaspam.ui.Add.AddScreen
import com.example.uaspam.ui.Add.DestinasiEntry
import com.example.uaspam.ui.Home.DestinasiHomeMotor
import com.example.uaspam.ui.Home.HomeScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiHomeMotor.route,
        modifier = Modifier
    ) {
        composable(DestinasiHomeMotor.route){
            HomeScreen(navigateToItemEntry = { navController.navigate(DestinasiEntry.route) })
        }
        composable(DestinasiEntry.route){
            AddScreen(navigateBack = { navController.popBackStack()})
        }
    }
}