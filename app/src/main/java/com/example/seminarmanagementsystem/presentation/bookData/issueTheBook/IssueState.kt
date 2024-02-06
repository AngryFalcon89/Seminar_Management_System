package com.example.seminarmanagementsystem.presentation.bookData.issueTheBook

import androidx.paging.PagingData
import com.example.seminarmanagementsystem.data.model.bookModel.IssueDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO

data class IssueState (
    val isLoading: Boolean = false,
    val message: String = "",
    val error: String = "",
    val email: String = "",
    val name: String = "",
    val returnDate: String = "",
    val remarks: String = "",
    val issueTo: IssueDTO = IssueDTO(
        email = "",
        name = "",
        returnDate = "",
        remarks = ""
    )
)