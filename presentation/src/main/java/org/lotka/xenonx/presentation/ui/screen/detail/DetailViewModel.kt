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
import org.lotka.xenonx.domain.model.CoinDetailModel
import org.lotka.xenonx.domain.usecase.GetCoinByIdUseCase
import org.lotka.xenonx.domain.usecase.GetCoinsLocalByIdUseCase
import org.lotka.xenonx.domain.usecase.UpdateCoinsDetailUseCase
import org.lotka.xenonx.domain.util.Constants.PARAM_COIN_ID
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class DetailViewModel@Inject constructor(
    private val getCoinByIdUseCase: GetCoinByIdUseCase,
    private val getCoinsLocalById: GetCoinsLocalByIdUseCase,
    private val updateCoinsDetail: UpdateCoinsDetailUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {


    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()


    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoinById(coinId)
           getCoinLocalById(coinId)
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
                            updateCoinDetail(coin)
                        }
                    }
                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = result.message ?: "An unexpected error occurred")
                        }
                    }
                    is Resource.Loading -> {
                        _state.update {
                            it.copy(isLoading = true)
                        }
                    }

                    else -> {}
                }
            }
        }
    }

    fun updateCoinDetail(coinDetail: CoinDetailModel) {
        viewModelScope.launch {
            updateCoinsDetail.invoke(coinDetail)
        }
    }

    fun getCoinLocalById(coinId: String) {
        viewModelScope.launch {
            getCoinsLocalById.invoke(coinId).collect { coinDetail ->
                _state.update {
                    it.copy(
                        coin = coinDetail
                    )
                }
            }
        }
    }



}