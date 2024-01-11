package com.example.uaspam.ui.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.uaspam.model.Motor
import com.example.uaspam.navigasi.DestinasiNavigasi
import com.example.uaspam.ui.DealerTopAppBar
import com.example.uaspam.ui.PenyediaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

object DestinasiHomeMotor : DestinasiNavigasi {
    override val route = "homemotor"
    override val titleRes = "Data Motor"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateBack: () -> Unit,
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeMotorViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DealerTopAppBar(
                title = "Motor",
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        },
    ) { innerPadding ->
        val uiStateSiswa by viewModel.homeUIState.collectAsState()
        BodyHome(
            itemMotor = uiStateSiswa.listMotor,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onMotorClick =  onDetailClick
        )
    }
}

@Composable
fun BodyHome(
    itemMotor: List<Motor>,
    modifier: Modifier = Modifier,
    onMotorClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemMotor.isEmpty()) {
            Text(
                text = "Tidak ada data Motor",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListMotor(
                itemMotor = itemMotor,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onMotorClick(it.no) }
            )
        }
    }
}


@Composable
fun ListMotor(
    itemMotor: List<Motor>,
    modifier: Modifier = Modifier,
    onItemClick: (Motor) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(items = itemMotor, key = { it.no }) { motor ->
            DataMotor(
                motor = motor,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(motor) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun DataMotor(
    motor: Motor,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Nama Merek : "+motor.merek,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                text = "Jenis Motor : "+motor.jenis,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "Keterangan : "+motor.keterangan,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}