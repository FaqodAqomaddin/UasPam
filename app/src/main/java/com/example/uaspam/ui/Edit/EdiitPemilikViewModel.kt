package com.example.uaspam.ui.Edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.PemilikRepository
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddEventPemilik
import com.example.uaspam.ui.AddUIStatePemilik
import com.example.uaspam.ui.toPemilik
import com.example.uaspam.ui.toUIStatePemilik
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first

class EditPemilikViewModel(
    savedStateHandle: SavedStateHandle,
    private val pemilikrepository: PemilikRepository
) : ViewModel() {

    var pemilikUiState by mutableStateOf(AddUIStatePemilik())
        private set

    private val pemilikId: String = checkNotNull(savedStateHandle[EditPemilikDestination.pemilikId])

    init {
        viewModelScope.launch {
            pemilikUiState =
                pemilikrepository.getPemilikById(pemilikId)
                    .filterNotNull()
                    .first()
                    .toUIStatePemilik()
        }
    }

    fun updateUIStatepemilik(addEventpemilik: AddEventPemilik) {
        pemilikUiState = pemilikUiState.copy(addEventPemilik = addEventpemilik)
    }

    suspend fun updatePemilik() {
        pemilikrepository.update(pemilikUiState.addEventPemilik.toPemilik())

    }
}