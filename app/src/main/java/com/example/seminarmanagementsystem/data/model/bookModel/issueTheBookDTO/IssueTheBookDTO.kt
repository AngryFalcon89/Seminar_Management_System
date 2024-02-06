package com.example.seminarmanagementsystem.data.model.bookModel.issueTheBookDTO

import com.example.seminarmanagementsystem.data.model.bookModel.Book

data class IssueTheBookDTO(
    val bookToBeIssued: Book,
    val message: String,
    val status: String
)