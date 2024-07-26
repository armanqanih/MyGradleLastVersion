package org.lotka.xenonx.presentation.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.lotka.xenonx.domain.usecase.SearchCoinUseCase
import org.lotka.xenonx.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCoinsUseCase: SearchCoinUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val _eventFlow = MutableStateFlow(SearchState())
    val eventFlow = _state.asStateFlow()

    private var searchJob: Job? = null

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.update { it.copy(searchQuery = event.query) }

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L) // Debounce delay
                    searchCoins(event.query)
                }
            }
            is SearchEvent.ShowSnakeBar -> {
                _state.update { it.copy(error = event.message) }
            }

            else -> {}
        }
    }

    private fun searchCoins(query: String) {
        viewModelScope.launch {
            searchCoinsUseCase(query).collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.update { it.copy(
                            coins = result.data ?: emptyList(),
                            isLoading = false
                        ) }
                    }
                    is Resource.Error -> {
                        _state.update { it.copy(
                            error = result.message ?: "An unexpected error occurred",
                            isLoading = false
                        ) }
                    }
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    else -> {}
                }
            }
        }
    }
}
