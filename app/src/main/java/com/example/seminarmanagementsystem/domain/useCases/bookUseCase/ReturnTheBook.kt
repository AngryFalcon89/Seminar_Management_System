package com.example.seminarmanagementsystem.domain.useCases.bookUseCase

import android.util.Log
import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.domain.repository.bookRepo.BookRepo
import com.example.seminarmanagementsystem.utils.Constants
import com.example.seminarmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class ReturnTheBook(
    private val bookRepo: BookRepo
) {
    operator fun invoke(
        bookId: String
    ): Flow<NetworkResult<Response<ResponseDTO>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val response = bookRepo.returnTheBook(bookId)
            Log.d(Constants.TAG, "invoke: ${response.body()}")
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Error occurred"))
        }
    }
}