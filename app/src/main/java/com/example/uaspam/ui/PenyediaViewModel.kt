package com.example.uaspam.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.uaspam.DealerApp
import com.example.uaspam.ui.Add.AddViewModel
import com.example.uaspam.ui.Add.AddViewModelPemilik
import com.example.uaspam.ui.Detail.DetailMotorViewModel
import com.example.uaspam.ui.Detail.DetailPemilikViewModel
import com.example.uaspam.ui.Edit.EditMotorViewModel
import com.example.uaspam.ui.Edit.EditPemilikViewModel
import com.example.uaspam.ui.Home.HomeMotorViewModel
import com.example.uaspam.ui.Home.HomePemilikViewModel

fun CreationExtras.apkikasiDealer(): DealerApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DealerApp)

object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            AddViewModel(apkikasiDealer().container.motorRepository)
        }

        initializer {
            HomeMotorViewModel(apkikasiDealer().container.motorRepository)
        }

        initializer {
            DetailMotorViewModel(
                createSavedStateHandle(),
                apkikasiDealer().container.motorRepository
            )
        }

        initializer {
            EditMotorViewModel(
                createSavedStateHandle(),
                apkikasiDealer().container.motorRepository
            )
        }
        initializer {
            AddViewModelPemilik(apkikasiDealer().container.pemilikRepository)
        }

        initializer {
            HomePemilikViewModel(apkikasiDealer().container.pemilikRepository)
        }

        initializer {
            DetailPemilikViewModel(
                createSavedStateHandle(),
                apkikasiDealer().container.pemilikRepository
            )
        }

        initializer {
            EditPemilikViewModel(
                createSavedStateHandle(),
                apkikasiDealer().container.pemilikRepository
            )
        }
    }
}