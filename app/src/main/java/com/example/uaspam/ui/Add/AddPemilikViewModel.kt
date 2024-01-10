package com.example.uaspam.ui.Add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.uaspam.data.MotorRepository
import com.example.uaspam.data.PemilikRepository
import com.example.uaspam.ui.AddEvent
import com.example.uaspam.ui.AddEventPemilik
import com.example.uaspam.ui.AddUIState
import com.example.uaspam.ui.AddUIStatePemilik
import com.example.uaspam.ui.toMotor
import com.example.uaspam.ui.toPemilik

class AddViewModelPemilik(private val pemilikRepository: PemilikRepository) : ViewModel() {

    var addUIStatePemilik by mutableStateOf(AddUIStatePemilik())
        private set

    fun updateAddUIStatePemilik(addEventPemilik: AddEventPemilik) {
        addUIStatePemilik = AddUIStatePemilik(addEventPemilik = addEventPemilik)
    }

    suspend fun addPemilik() {
        pemilikRepository.save(addUIStatePemilik.addEventPemilik.toPemilik())
    }
}