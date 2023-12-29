@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.kontakappwithapi.ui.edit.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kontakappwithapi.navigation.DestinasiNavigasi
import com.example.kontakappwithapi.ui.PenyediaViewModel
import com.example.kontakappwithapi.ui.TopAppBarKontak
import com.example.kontakappwithapi.ui.edit.viewmodel.EditViewModel
import com.example.kontakappwithapi.ui.home.screen.DestinasiHome
import com.example.kontakappwithapi.ui.kontak.screen.EntryKontakBody
import kotlinx.coroutines.launch

object EditDestination: DestinasiNavigasi {
    override val route = "edit"
    override val titleRes = "Edit Kontak"
    const val kontakId = "itemId"
    val routeWithArg ="${route}/{${kontakId}}"
}

@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
            )
        },
        modifier = modifier
    ) {innerPadding ->
        EntryKontakBody(
            insertUiState = viewModel.editKotakState,
            onSiswaValueChange = viewModel::updateInsertKOntakState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKontak()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}