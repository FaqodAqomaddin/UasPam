package com.example.uaspam.ui.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uaspam.data.PemilikRepository
import com.example.uaspam.model.Pemilik
import com.example.uaspam.ui.HomeUIState
import com.example.uaspam.ui.HomeUIStatePemilik
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed class PemilikUIState {
    data class Success(val pemilik: Flow<List<Pemilik>>) : PemilikUIState()
    object Error : PemilikUIState()
    object Loading : PemilikUIState()
}

class HomePemilikViewModel(private val pemiilikRepository: PemilikRepository) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIStatePemilik: StateFlow<HomeUIStatePemilik> = pemiilikRepository.getAll()
        .filterNotNull()
        .map {
            HomeUIStatePemilik (listPemilik = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIStatePemilik()

        )

}