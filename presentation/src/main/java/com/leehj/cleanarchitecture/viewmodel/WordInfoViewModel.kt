package com.leehj.cleanarchitecture.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leehj.cleanarchitecture.widget.WordInfoState
import com.leehj.domain.usecase.GetWordInfo
import com.leehj.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    private val getWordInfo: GetWordInfo
) : ViewModel() {
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            if (query.isNotEmpty()) {
                getWordInfo(query)
                    .onEach { result ->
                        when (result) {
                            is Result.Success -> {
                                _state.value = state.value.copy(
                                    wordInfoItems = result.data ?: emptyList(),
                                    isLoading = false
                                )
                            }

                            is Result.Error -> {
                                _state.value = state.value.copy(
                                    wordInfoItems = result.data ?: emptyList(),
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UIEvent.ShowSnackbar(
                                        result.message ?: "unknown error!"
                                    )
                                )
                            }

                            is Result.Loading -> {
                                _state.value = state.value.copy(
                                    wordInfoItems = result.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }
                    }.launchIn(this)
            } else{
                _state.value = state.value.copy(
                    wordInfoItems = emptyList(),
                    isLoading = false
                )
            }
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}