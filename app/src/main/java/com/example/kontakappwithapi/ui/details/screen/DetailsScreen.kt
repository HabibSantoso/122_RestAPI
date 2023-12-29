@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.kontakappwithapi.ui.details.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kontakappwithapi.model.Kontak
import com.example.kontakappwithapi.navigation.DestinasiNavigasi
import com.example.kontakappwithapi.ui.PenyediaViewModel
import com.example.kontakappwithapi.ui.TopAppBarKontak
import com.example.kontakappwithapi.ui.details.viewmodel.DetailsKontakUiState
import com.example.kontakappwithapi.ui.details.viewmodel.DetailsViewModel
import com.example.kontakappwithapi.ui.home.screen.DestinasiHome
import com.example.kontakappwithapi.ui.home.screen.OnError
import com.example.kontakappwithapi.ui.home.screen.OnLoading
import com.example.kontakappwithapi.ui.kontak.screen.DestinasiEntry

object DetailsDestination : DestinasiNavigasi {
    override val route = "item_details"
    override val titleRes = "detail"
    const val kontakId = "itemId"
    val routeWithArg ="$route/{$kontakId}"
}

@Composable
fun DetailsScreen(
    onEditClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    detailsViewModel: DetailsViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                )
        }
    ) {innerPadding ->
        DetailStatus(
            kontakUiState = detailsViewModel.detailsKontakUiState,
            retryAtion = { detailsViewModel.getKontakById() },
            onEditClick = onEditClick,
            modifier = Modifier.padding(innerPadding).fillMaxSize())
    }

}

@Composable
fun DetailStatus(
    kontakUiState: DetailsKontakUiState,
    retryAtion: () -> Unit,
    modifier: Modifier = Modifier,
    onEditClick: (Int) -> Unit
) {
    when (kontakUiState) {
        is DetailsKontakUiState.Success -> {
            KontakLayout(
                kontak = kontakUiState.kontak,
                modifier = modifier.padding(16.dp),
                onEditClick = {
                    onEditClick(it)
                }
            )
        }

        is DetailsKontakUiState.Loading -> {
            OnLoading(modifier = modifier)
        }

        is DetailsKontakUiState.Error -> {
            OnError(retryAction = retryAtion, modifier = modifier)
        }
    }
}

@Composable
fun KontakLayout(
    kontak: Kontak,
    modifier: Modifier = Modifier,
    onEditClick:(Int) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        KontakCard(
            kontak = kontak,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(
            onClick = {
                onEditClick(kontak.id)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Text(text = "Edit")
        }
    }
}

@Composable
fun KontakCard(
    kontak: Kontak,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = kontak.nama,
                    style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null)
                Text(
                    text = kontak.nohp,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = kontak.alamat,
                    style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.weight(1f))
            }
        }
    }
}