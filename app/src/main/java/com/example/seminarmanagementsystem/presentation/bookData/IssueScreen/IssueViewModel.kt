package com.example.seminarmanagementsystem.presentation.bookData.IssueScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.seminarmanagementsystem.domain.useCases.bookUseCase.BookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssueViewModel @Inject constructor(
    private val bookUseCase: BookUseCase
) : ViewModel() {
    val issueBook = bookUseCase.issueBook().cachedIn(viewModelScope)
}