package com.example.uaspam.ui.Edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.uaspam.navigasi.DestinasiNavigasi
import com.example.uaspam.ui.Add.EntryBody
import com.example.uaspam.ui.DealerTopAppBar
import com.example.uaspam.ui.PenyediaViewModel
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel

object EditMotorDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes ="Edit Motor"
    const val MotorId = "itemId"
    val routeWithArgs = "${EditMotorDestination.route}/{$MotorId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditMotorViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            DealerTopAppBar(
                title =EditMotorDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBody(
            addUIState = viewModel.kontakUiState,
            onMotorValueChange = viewModel::updateUIState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKontak()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}
