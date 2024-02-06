package com.example.seminarmanagementsystem.presentation.bookData.updateBook

import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.data.model.bookModel.issueBook.IssuedTo

data class UpdateState(
    val isLoading: Boolean = false,
    val message: String = "",
    val error: String = "",
    val _id:String = "",
    val accessionNumber: String = "",
    val author: String = "",
    val author1: String = "",
    val author2: String = "",
    val author3: String = "",
    val category1: String = "",
    val category2: String = "",
    val category3: String = "",
    val edition: String = "",
    val id: String = "",
    val malAccNumber: String = "",
    val publisher: String = "",
    val publishingYear: String = "",
    val title: String = "",
    var bookRequest: AddBookRequestDTO = AddBookRequestDTO(
        Accession_Number = "",
        Author = "",
        Author1 = "",
        Author2 = "",
        Author3 = "",
        Category1 = "",
        Category2 = "",
        Category3 = "",
        Edition = "",
        ID = "",
        MAL_ACC_Number = "",
        Publisher = "",
        Publishing_Year = "",
        Title = "",
    )
)