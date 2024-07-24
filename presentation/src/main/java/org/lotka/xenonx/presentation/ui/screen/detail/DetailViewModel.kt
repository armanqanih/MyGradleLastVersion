package org.lotka.xenonx.presentation.ui.screen.detail

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.GetCoinByIdUseCase
import org.lotka.xenonx.domain.util.Constants.PARAM_COIN_ID
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class DetailViewModel@Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
     savedStateHandle: SavedStateHandle
):ViewModel() {


    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()


    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinById(coinId)
        }
    }

    private fun getCoinById(coinId: String) {
        viewModelScope.launch {
            getCoinByIdUseCase.invoke(coinId).collect { result ->
                when(result) {
                    is Resource.Success -> {
                        result.data?.let { coin ->
                            _state.update {
                                it.copy(
                                    coin = coin,
                                    isLoading = false

                                )
                            }
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(error = result.message ?: "An unexpected error occurred")
                        }
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }
                }
            }
        }
    }



}