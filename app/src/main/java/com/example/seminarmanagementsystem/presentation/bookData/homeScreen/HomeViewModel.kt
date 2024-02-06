package com.example.seminarmanagementsystem.presentation.bookData.homeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.BookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
) : ViewModel() {
    val book = bookUseCase.getBookListUseCase().cachedIn(viewModelScope)
}