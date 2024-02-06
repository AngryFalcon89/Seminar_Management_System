package com.example.seminarmanagementsystem.presentation.bookData.searchScreen

import androidx.paging.PagingData
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val books: Flow<PagingData<Book>>? = null,
    val isSearched: Boolean = false
)
