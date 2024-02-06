package com.example.seminarmanagementsystem.domain.useCases.authUseCases

import android.util.Log
import com.example.seminarmanagementsystem.data.model.authModel.UserRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.UserResponseDTO
import com.example.seminarmanagementsystem.domain.repository.authRepo.AuthRepo
import com.example.seminarmanagementsystem.utils.Constants.TAG
import com.example.seminarmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(
        userRequest: UserRequestDTO
    ): Flow<NetworkResult<Response<UserResponseDTO>>> = flow {
        try {
            emit(NetworkResult.Loading())
            Log.d(TAG, "invoke: ${userRequest}")
            val response = authRepo.login(userRequest)
            Log.d(TAG, "invoke: ${response.body()}")
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Error occurred"))
        }
    }
}