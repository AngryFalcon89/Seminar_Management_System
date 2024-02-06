package com.example.seminarmanagementsystem.domain.useCases.authUseCases.forgetPasswordUseCase

import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpResponseDTO
import com.example.seminarmanagementsystem.domain.repository.authRepo.AuthRepo
import com.example.seminarmanagementsystem.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class VerifyOtpForExistingEmailUseCase @Inject constructor(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(
        verifyOtpRequestDTO: VerifyOtpRequestDTO
    ): Flow<NetworkResult<Response<VerifyOtpResponseDTO>>> = flow {
        try {
            emit(NetworkResult.Loading())
            val response = authRepo.verifyOtpForExistingEmail(verifyOtpRequestDTO)
            emit(NetworkResult.Success(response))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An Unexpected error occurred"))
        } catch (e: IOException) {
            emit(NetworkResult.Error("Error occurred"))
        }
    }
}
