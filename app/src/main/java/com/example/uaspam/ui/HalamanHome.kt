package com.example.uaspam.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uaspam.R
import com.example.uaspam.navigasi.DestinasiNavigasi

object DestinasiHomePage : DestinasiNavigasi {
    override val route = "homePage"
    override val titleRes = "Home"
}

@Composable
fun Homepage(
    navigateToPemilik: () -> Unit,
    navigateToMotor: () -> Unit
){
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            border = BorderStroke(1.dp, Color.Cyan),
            modifier = Modifier
                .fillMaxWidth()
                .padding(50.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")

                Text(
                    text = "Aplikasi",
                    color = Color.DarkGray,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 35.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "Dealer",
                    color = Color.DarkGray,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 55.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )


            }

        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f, false),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick =
                    navigateToPemilik

            ) {
                Text(text = "Pemilik")

            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick =
                    navigateToMotor

            ) {
                Text(text = "Motor")

            }

        }

    }
}
