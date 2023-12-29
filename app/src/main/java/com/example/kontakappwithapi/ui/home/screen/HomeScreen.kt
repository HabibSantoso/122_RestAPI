@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kontakappwithapi.ui.home.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kontakappwithapi.navigation.DestinasiNavigasi
import com.example.kontakappwithapi.ui.PenyediaViewModel
import com.example.kontakappwithapi.ui.TopAppBarKontak
import com.example.kontakappwithapi.ui.home.viewmodel.HomeViewModel


object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "kontak"
}
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Kontak")
            }
        }
    ) {innerPadding ->
        HomeStatus(
            kontakUIState = viewModel.kontakUIState,
            retryAction = {
                viewModel.getKontak()
            },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteKontak(it.id)
                viewModel.getKontak()
            })

    }
}