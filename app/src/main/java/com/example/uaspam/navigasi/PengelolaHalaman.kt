package com.example.uaspam.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uaspam.ui.Add.AddScreen
import com.example.uaspam.ui.Add.DestinasiEntry

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = DestinasiEntry.route,
        modifier = Modifier
    ) {
        composable(DestinasiEntry.route){
            AddScreen(navigateBack = { /*TODO*/ })
        }
    }
}