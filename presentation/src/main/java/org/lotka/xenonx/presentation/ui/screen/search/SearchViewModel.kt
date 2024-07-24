package org.lotka.xenonx.presentation.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.SearchCoinUseCase
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCoinUseCase: SearchCoinUseCase
): ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()



    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
             _state.update { it.copy(searchQuery = event.query) } }
            is SearchEvent.SearchCoins -> {
                searchCoins()
            }
        }
    }



    fun searchCoins() {
        viewModelScope.launch {
            val query = _state.value.searchQuery
            searchCoinUseCase(query).collect{result->
                when(result){
                    is Resource.Loading -> {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                    }
                    is Resource.Success -> {
                        result.data?.let { coins ->
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    coins = coins)
                            }
                        }
                    }
                    is Resource.Error -> {
                  _state.update {
                      it.copy(
                          isLoading = false,
                          error = result.message
                      )
                  } } }


            }
        }

    }


}