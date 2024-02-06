package com.example.seminarmanagementsystem.data.apiService

import IssueBookDTO
import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.data.model.bookModel.IssueDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.data.model.bookModel.issueTheBookDTO.IssueTheBookDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApiService {

    @GET("/books/issued-books")
    suspend fun issueBooks(@Query("limit") limit: Int, @Query("page") page: Int): IssueBookDTO

    @POST("/books")
    suspend fun addBook(@Body addBookRequestDTO: AddBookRequestDTO): Response<AddBookDTO>

    @DELETE("/books/{id}")
    suspend fun deleteBook(@Path("id") id: String): Response<ResponseDTO>

    @PATCH("/books/{id}")
    suspend fun updateBook(@Path("id") id: String,@Body book: AddBookRequestDTO): Response<ResponseDTO>

    @POST("/books/{id}/issue")
    suspend fun issueTheBook(@Path("id") id: String, @Body issueDTO: IssueDTO):Response<IssueTheBookDTO>

    @POST("/books/{id}/return")
    suspend fun returnTheBook(@Path("id") id: String):Response<ResponseDTO>

}