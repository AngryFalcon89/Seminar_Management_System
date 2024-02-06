package com.example.seminarmanagementsystem.data.repository.bookRepo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.seminarmanagementsystem.data.apiService.ApiService
import com.example.seminarmanagementsystem.data.apiService.AuthApiService
import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.data.model.bookModel.IssueDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.data.model.bookModel.issueTheBookDTO.IssueTheBookDTO
import com.example.seminarmanagementsystem.data.pagingSource.BooksPagingSource
import com.example.seminarmanagementsystem.data.pagingSource.IssueBookPagingSource
import com.example.seminarmanagementsystem.data.pagingSource.SearchBookPagingSource
import com.example.seminarmanagementsystem.domain.repository.bookRepo.BookRepo
import com.example.seminarmanagementsystem.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class BookRepoImpl(
    private val apiService : ApiService,
    private val authApiService: AuthApiService
): BookRepo {
    override fun getBooks(): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                BooksPagingSource(apiService = apiService)
            }
        ).flow
    }
    override fun searchBooks(
        searchQuery: String
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                SearchBookPagingSource(
                    searchQuery = searchQuery,
                    apiService = apiService
                )
            }
        ).flow
    }

    override fun issueBook(): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                IssueBookPagingSource(
                    authApiService = authApiService
                )
            }
        ).flow
    }

    override suspend fun addBook(addBookRequestDTO: AddBookRequestDTO): Response<AddBookDTO>{
        return authApiService.addBook(addBookRequestDTO = addBookRequestDTO)
    }

    override suspend fun deleteBook(bookId:String):Response<ResponseDTO>{
        return authApiService.deleteBook(bookId)
    }

    override suspend fun updateBook(bookId: String, book: AddBookRequestDTO): Response<ResponseDTO>{
        return authApiService.updateBook(id = bookId, book = book)
    }

    override suspend fun issueTheBook(bookId: String, issueTo: IssueDTO): Response<IssueTheBookDTO>{
        return authApiService.issueTheBook(id = bookId, issueTo)
    }

    override suspend fun returnTheBook(bookId: String): Response<ResponseDTO>{
        return authApiService.returnTheBook(id = bookId)
    }
}