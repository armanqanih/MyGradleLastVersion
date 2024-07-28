package org.lotka.xenonx.presentation.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.GetLocalCoinsUseCase
import javax.inject.Inject

@HiltViewModel
class BookMarkViewModel @Inject constructor(
    private val getCoin: GetLocalCoinsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookMarkState())
    val state = _state.asStateFlow()

    init {
        fetchSavedCoins()
    }

    private fun fetchSavedCoins() {
        viewModelScope.launch {
            getCoin.invoke().collect { coins ->
                _state.update { it.copy(coins = coins) }
            }
        }
    }
}
