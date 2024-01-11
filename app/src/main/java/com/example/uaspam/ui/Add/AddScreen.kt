package com.example.uaspam.ui.Add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.navigasi.DestinasiNavigasi
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddUIState
import com.example.uaspam.ui.DealerTopAppBar
import com.example.uaspam.ui.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiEntry : DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Input Data Motor"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    addViewModel: AddViewModel = viewModel(factory = PenyediaViewModel.Factory),

    ) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var pemilikList by remember { mutableStateOf(emptyList<String>()) }

    // Ambil data dari Firestore saat komponen pertama kali dibuat
    LaunchedEffect(key1 = true) {
        addViewModel.getNamaList()
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DealerTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->

        EntryBody(
            addUIState = addViewModel.addUIState,
            onMotorValueChange = addViewModel::updateAddUIState,
            onSaveClick = {
                coroutineScope.launch {
                    addViewModel.addKontak()
                    navigateBack()
                }
            },
            pemilikList = pemilikList,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    addUIState: AddUIState,
    onMotorValueChange: (AddEvent) -> Unit,
    onSaveClick: () -> Unit,
    pemilikList: List<String>,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            addEvent = addUIState.addEvent,
            onValueChange = onMotorValueChange,
            pemilikList = pemilikList,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    addEvent: AddEvent,
    pemilikList: List<String>,
    modifier: Modifier = Modifier,
    onValueChange: (AddEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = addEvent.merek,
            onValueChange = { onValueChange(addEvent.copy(merek = it)) },
            label = { Text("Merek") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addEvent.jenis,
            onValueChange = { onValueChange(addEvent.copy(jenis = it)) },
            label = { Text("Jenis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = addEvent.keterangan,
            onValueChange = { onValueChange(addEvent.copy(keterangan = it)) },
            label = { Text(text = "Keterangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        PemilikRadioGroup(
            pemilikList = pemilikList,
            selectedPemilik = addEvent.pemilik,
            onPemilikSelected = { onValueChange(addEvent.copy(pemilik = it)) },
            enabled = enabled
        )

    }
}

@Composable
fun PemilikRadioGroup(
    pemilikList: List<String>,
    selectedPemilik: String,
    onPemilikSelected: (String) -> Unit,
    enabled: Boolean
) {
    Column {
        Text("Pemilik")
        Spacer(modifier = Modifier.height(4.dp))

        pemilikList.forEach { pemilik ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                RadioButton(
                    selected = pemilik == selectedPemilik,
                    onClick = { onPemilikSelected(pemilik) },
                    enabled = enabled
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = pemilik)
            }
        }
    }
}