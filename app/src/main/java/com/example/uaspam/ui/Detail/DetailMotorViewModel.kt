package com.example.uaspam.ui.Detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.MotorRepository
import com.example.uaspam.ui.DetailUIState
import com.example.uaspam.ui.toDetailMotor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailMotorViewModel(
    savedStateHandle: SavedStateHandle,
    private val motorrepository: MotorRepository
) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val motorId: String = checkNotNull(savedStateHandle[DetailMotorDestination.MotorId])

    val uiState: StateFlow<DetailUIState> =
        motorrepository.getMotorById(motorId)
            .filterNotNull()
            .map {
                DetailUIState(addEvent = it.toDetailMotor())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )

    suspend fun deleteKontak() {
        motorrepository.delete(motorId)

    }


}