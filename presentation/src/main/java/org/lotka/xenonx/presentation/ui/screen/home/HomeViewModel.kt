package org.lotka.xenonx.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.model.CoinModel
import org.lotka.xenonx.domain.usecase.GetCoinsUseCase
import org.lotka.xenonx.domain.usecase.GetLocalCoinsUseCase
import org.lotka.xenonx.domain.usecase.UpdateCoinsUseCase
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val getCoinsUseCase: GetCoinsUseCase,
    private val updateCoins: UpdateCoinsUseCase,
    private val getCoinFromDataBase: GetLocalCoinsUseCase
):ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

     val pages = _state.value.page


    init {
        getCoins(page = pages)
        getCoinsOfDataBase()
    }



    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.GetNextPage -> {
                getCoins(page = pages)
            }

            HomeEvent.navigate -> {
                _state.update {it.copy(isNavigateToNewPage =! state.value.isNavigateToNewPage) } }

            else -> {}
        }
    }



    private fun getCoins(page:Int) {
        viewModelScope.launch {
            getCoinsUseCase(page).collect { result ->
            when(result){
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = result.message ?: "Check your internet connection") } }
                is Resource.Loading -> {
                    _state.update {it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                   result.data?.let { coins ->
                       _state.update {
                           it.copy(
                               isLoading = false,
                               coins = coins, page = it.page + 1)
                       }

                       updateCoinsOfDataBase(coins)

                   }
                }

                else -> {}
            }

            }
        }
    }


    private fun updateCoinsOfDataBase(coins: List<CoinModel>) {
        viewModelScope.launch {
            updateCoins(coins)
        }
    }

     fun getCoinsOfDataBase() {
         viewModelScope.launch {
             getCoinFromDataBase().collect { coins ->
                 _state.update {
                     it.copy(
                         coins = coins,
                         isLoading = false

                     ) }
             }
         }
     }




}