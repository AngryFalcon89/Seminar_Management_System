package com.example.seminarmanagementsystem.domain.repository.bookRepo

import androidx.paging.PagingData
import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.data.model.bookModel.IssueDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.data.model.bookModel.issueTheBookDTO.IssueTheBookDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface BookRepo {
    fun getBooks(): Flow<PagingData<Book>>
    fun searchBooks(searchQuery: String): Flow<PagingData<Book>>
    fun issueBook(): Flow<PagingData<Book>>
    suspend fun addBook(addBookRequestDTO: AddBookRequestDTO): Response<AddBookDTO>
    suspend fun deleteBook(bookId: String): Response<ResponseDTO>
    suspend fun updateBook(bookId: String, book: AddBookRequestDTO): Response<ResponseDTO>
    suspend fun issueTheBook(bookId: String, issueTo: IssueDTO): Response<IssueTheBookDTO>
    suspend fun returnTheBook(bookId: String): Response<ResponseDTO>
}