package com.example.kontakappwithapi.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.kontakappwithapi.KontakAplication
import com.example.kontakappwithapi.ui.details.viewmodel.DetailsViewModel
import com.example.kontakappwithapi.ui.edit.viewmodel.EditViewModel
import com.example.kontakappwithapi.ui.home.viewmodel.HomeViewModel
import com.example.kontakappwithapi.ui.kontak.viewmodel.InsertViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiMars().container.kontakRepository)
        }

        initializer {
            InsertViewModel(aplikasiMars().container.kontakRepository)
        }

        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                kontakRepository = aplikasiMars().container.kontakRepository)
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                kontakRepository =  aplikasiMars().container.kontakRepository)
        }
    }
}

fun CreationExtras.aplikasiMars(): KontakAplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as KontakAplication)