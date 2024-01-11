package com.example.uaspam.ui.Add

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uaspam.data.MotorRepository
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddUIState
import com.example.uaspam.ui.toMotor
import kotlinx.coroutines.flow.first

class AddViewModel(private val motorRepository: MotorRepository) : ViewModel() {


    // Menambahkan state untuk menyimpan daftar nama
    private val _namaList = mutableStateOf<List<String>>(emptyList())
    val namaList: State<List<String>> get() = _namaList
    var addUIState by mutableStateOf(AddUIState())
        private set

    fun updateAddUIState(addEvent: AddEvent) {
        addUIState = AddUIState(addEvent = addEvent)
    }

    suspend fun getNamaList() {
        _namaList.value = motorRepository.getNamaList().first()
    }

    suspend fun addKontak() {
        motorRepository.save(addUIState.addEvent.toMotor())
    }
}