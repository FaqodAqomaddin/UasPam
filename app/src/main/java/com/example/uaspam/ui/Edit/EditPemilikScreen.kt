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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.navigasi.DestinasiNavigasi
import com.example.uaspam.ui.Add.EntryBodyPemilik
import com.example.uaspam.ui.DealerTopAppBar
import com.example.uaspam.ui.PenyediaViewModel
import kotlinx.coroutines.launch

object EditPemilikDestination : DestinasiNavigasi {
    override val route = "item_edit_pemilik"
    override val titleRes ="Edit Pemilik"
    const val pemilikId = "itemId"
    val routeWithArgs = "${EditPemilikDestination.route}/{$pemilikId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPemilikScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditPemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            DealerTopAppBar(
                title =EditPemilikDestination.titleRes,
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryBodyPemilik(
            addUIStatePemilik = viewModel.pemilikUiState,
            onPemilikValueChange = viewModel::updateUIStatepemilik,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePemilik()
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