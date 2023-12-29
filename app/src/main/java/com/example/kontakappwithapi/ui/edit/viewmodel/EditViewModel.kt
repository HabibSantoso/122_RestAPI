package com.example.kontakappwithapi.ui.edit.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kontakappwithapi.repository.KontakRepository
import com.example.kontakappwithapi.ui.edit.screen.EditDestination
import com.example.kontakappwithapi.ui.kontak.viewmodel.InsertUiEvent
import com.example.kontakappwithapi.ui.kontak.viewmodel.InsertUiState
import com.example.kontakappwithapi.ui.kontak.viewmodel.toKontak
import com.example.kontakappwithapi.ui.kontak.viewmodel.toUiStateKontak
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository
): ViewModel() {
    var editKotakState by mutableStateOf(InsertUiState())
    val kontakId: Int = checkNotNull(savedStateHandle[EditDestination.kontakId])

    init {
        viewModelScope.launch {
            editKotakState = kontakRepository.getKontakById(kontakId).toUiStateKontak()
        }
    }

    fun updateInsertKOntakState(insertUiEvent: InsertUiEvent) {
        editKotakState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateKontak() {
        viewModelScope.launch {
            try {
                kontakRepository.updateKontak(kontakId, editKotakState.insertUiEvent.toKontak())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}