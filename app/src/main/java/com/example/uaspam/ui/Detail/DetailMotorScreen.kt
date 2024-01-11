package com.example.uaspam.ui.Detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.model.Motor
import com.example.uaspam.navigasi.DestinasiNavigasi
import com.example.uaspam.ui.DealerTopAppBar
import com.example.uaspam.ui.DetailUIState
import com.example.uaspam.ui.PenyediaViewModel
import com.example.uaspam.ui.toMotor
import kotlinx.coroutines.launch

object DetailMotorDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = "Detail Motor"
    const val MotorId = "itemId"
    val routeWithArgs = "$route/{$MotorId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMotorScreen(
    navigateToEditItem: (String) -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailMotorViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            DealerTopAppBar(
                title = DetailMotorDestination.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToEditItem(uiState.value.addEvent.no) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)

            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "",
                )
            }
        }, modifier = modifier
    ) { innerPadding ->
        ItemDetailsBody(
            detailUIState = uiState.value,
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteKontak()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),

            )
    }
}

@Composable
private fun ItemDetailsBody(
    detailUIState: DetailUIState,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
        ItemDetails(
            motor  = detailUIState.addEvent.toMotor(), modifier = Modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = { deleteConfirmationRequired = true },
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Delete")
        }
        if (deleteConfirmationRequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationRequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationRequired = false },
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}

@Composable
fun ItemDetails(
    motor: Motor, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ItemDetailsRow(
                labelResID ="Merek Motor",
                itemDetail = motor.merek,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRow(
                labelResID = "Jenis Motor",
                itemDetail = motor.jenis,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRow(
                labelResID ="Keterangan",
                itemDetail = motor.keterangan,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
            ItemDetailsRow(
                labelResID ="Pemilik",
                itemDetail = motor.pemilik,
                modifier = Modifier.padding(
                    horizontal = 12.dp
                )
            )
        }

    }
}


@Composable
private fun ItemDetailsRow(
    labelResID: String, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = labelResID, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { /* Do nothing */ },
        title = { Text("Are you sure") },
        text = { Text("Delete") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        })
}