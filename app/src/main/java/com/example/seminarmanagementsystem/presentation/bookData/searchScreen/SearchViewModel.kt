package com.example.seminarmanagementsystem.presentation.bookData.searchScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.log
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.BookUseCase
import com.example.seminarmanagementsystem.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                _state.value = _state.value.copy(isSearched = true)
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val books = bookUseCase.searchBook(
            searchQuery = _state.value.searchQuery
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(books = books)
    }
}