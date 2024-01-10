package com.example.uaspam.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.MotorRepository
import com.example.uaspam.model.Motor
import com.example.uaspam.ui.HomeUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class KontakUIState {
    data class Success(val kontak: Flow<List<Motor>>) : KontakUIState()
    object Error : KontakUIState()
    object Loading : KontakUIState()
}

class HomeMotorViewModel(private val motorRepository: MotorRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = motorRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listMotor = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )

}
