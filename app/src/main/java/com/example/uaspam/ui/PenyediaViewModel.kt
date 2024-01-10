package com.example.uaspam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.DealerApp
import com.example.uaspam.ui.Add.AddViewModel

fun CreationExtras.apkikasiDealer(): DealerApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DealerApp)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel(apkikasiDealer().container.motorRepository)
        }

//        initializer {
//            HomeViewModel(apkikasiKontak().container.kontakRepository)
//        }
//
//        initializer {
//            DetailViewModel(
//                createSavedStateHandle(),
//                apkikasiKontak().container.kontakRepository
//            )
//        }
//
//        initializer {
//            EditViewModel(
//                createSavedStateHandle(),
//                apkikasiKontak().container.kontakRepository
//            )
//        }
    }
}