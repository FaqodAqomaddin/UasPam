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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.model.Pemilik
import com.example.uaspam.navigasi.DestinasiNavigasi
import com.example.uaspam.ui.DealerTopAppBar
import com.example.uaspam.ui.PenyediaViewModel

object DestinasiHomePemilik : DestinasiNavigasi {
    override val route = "homepemilik"
    override val titleRes = "Pemilik"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPemilik(
    navigateBack: () -> Unit,
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomePemilikViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DealerTopAppBar(
                title = "Pemilik Motor",
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
        val uiStatePemilik by viewModel.homeUIStatePemilik.collectAsState()
        BodyPemilik(
            itemPemilik = uiStatePemilik.listPemilik,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onPemilikClick =  onDetailClick
        )
    }
}


@Composable
fun BodyPemilik(
    itemPemilik: List<Pemilik>,
    modifier: Modifier = Modifier,
    onPemilikClick: (String) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemPemilik.isEmpty()) {
            Text(
                text = "Tidak ada data Pemilik",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListPemilik(
                itemPemilik = itemPemilik,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                onItemClick = { onPemilikClick(it.id) }
            )
        }
    }
}

@Composable
fun ListPemilik(
    itemPemilik: List<Pemilik>,
    modifier: Modifier = Modifier,
    onItemClick: (Pemilik) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        this.items(items=itemPemilik, key = { it.id }) { pemilik ->
            Datapemilik(
                pemilik = pemilik,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(pemilik) }
            )
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun Datapemilik(
    pemilik: Pemilik,
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
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = pemilik.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = pemilik.telpon,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = pemilik.alamat,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}