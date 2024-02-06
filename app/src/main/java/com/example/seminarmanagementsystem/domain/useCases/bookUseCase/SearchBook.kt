package com.example.seminarmanagementsystem.domain.useCases.bookUseCase

import androidx.paging.PagingData
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.domain.repository.bookRepo.BookRepo
import kotlinx.coroutines.flow.Flow

class SearchBook(
    private val bookRepo: BookRepo
) {
    operator fun invoke(searchQuery: String): Flow<PagingData<Book>> {
        return bookRepo.searchBooks(
            searchQuery = searchQuery
        )
    }
}