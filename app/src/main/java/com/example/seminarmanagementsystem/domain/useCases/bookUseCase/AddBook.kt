package com.example.seminarmanagementsystem.domain.useCases.bookUseCase

import android.util.Log
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.domain.repository.bookRepo.BookRepo
import com.example.seminarmanagementsystem.utils.Constants.TAG
import com.example.seminarmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class AddBook (
    private val bookRepo: BookRepo
) {
    operator fun invoke(
        addBookRequestDTO: AddBookRequestDTO
    ): Flow<NetworkResult<Response<AddBookDTO>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val response = bookRepo.addBook(addBookRequestDTO)
            Log.d(TAG, "invoke: ${response.body()}")
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Error occurred"))
        }
    }
}