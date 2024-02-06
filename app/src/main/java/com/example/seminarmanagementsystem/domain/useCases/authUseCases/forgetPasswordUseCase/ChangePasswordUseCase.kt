package com.example.seminarmanagementsystem.domain.useCases.authUseCases.forgetPasswordUseCase

import com.example.seminarmanagementsystem.data.model.authModel.NewPasswordDTO
import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.domain.repository.authRepo.AuthRepo
import com.example.seminarmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(
        authorization: String,
        newPasswordDTO: NewPasswordDTO
    ): Flow<NetworkResult<Response<ResponseDTO>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val response = authRepo.changePasswordForExistingEmail(authorization, newPasswordDTO)
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Error occurred"))
        }
    }
}
