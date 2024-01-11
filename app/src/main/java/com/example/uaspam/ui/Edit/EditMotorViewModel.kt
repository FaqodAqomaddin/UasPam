package com.example.uaspam.ui.Edit

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.MotorRepository
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddUIState
import com.example.uaspam.ui.toMotor
import com.example.uaspam.ui.toUIStateMotor
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditMotorViewModel(
    savedStateHandle: SavedStateHandle,
    private val motorrepository: MotorRepository
) : ViewModel() {

    var kontakUiState by mutableStateOf(AddUIState())
        private set

    private val _namaList = mutableStateOf<List<String>>(emptyList())
    val namaList: State<List<String>> get() = _namaList

    private val motorId: String = checkNotNull(savedStateHandle[EditMotorDestination.MotorId])

    init {
        viewModelScope.launch {
            kontakUiState =
                motorrepository.getMotorById(motorId)
                    .filterNotNull()
                    .first()
                    .toUIStateMotor()
        }
    }

    suspend fun getNamaList() {
        _namaList.value = motorrepository.getNamaList().first()
    }


    fun updateUIState(addEvent: AddEvent) {
        kontakUiState = kontakUiState.copy(addEvent = addEvent)
    }

    suspend fun updateKontak() {
        motorrepository.update(kontakUiState.addEvent.toMotor())

    }
}