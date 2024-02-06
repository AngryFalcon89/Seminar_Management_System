package com.example.seminarmanagementsystem.data.model.bookModel

data class BooksDTO(
    val books: List<Book>,
    val currentPage: Int,
    val message: String,
    val status: String,
    val totalPages: Int
)