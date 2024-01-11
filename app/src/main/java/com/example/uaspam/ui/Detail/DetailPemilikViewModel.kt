package com.example.uaspam.ui.Detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.PemilikRepository
import com.example.uaspam.ui.DetailUIStatePemilik
import com.example.uaspam.ui.toDetailPemilik
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailPemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemilikrepository: PemilikRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val pemilikId: String = checkNotNull(savedStateHandle[DetailPemilikDestination.pemilikId])

    val pemilikuiState: StateFlow<DetailUIStatePemilik> =
        pemilikrepository.getPemilikById(pemilikId)
            .filterNotNull()
            .map {
                DetailUIStatePemilik(addEventPemilik = it.toDetailPemilik())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIStatePemilik()
            )

    suspend fun deleteKontak() {
        pemilikrepository.delete(pemilikId)

    }


}