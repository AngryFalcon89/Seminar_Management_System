package com.example.seminarmanagementsystem.domain.repository.authRepo

import com.example.seminarmanagementsystem.data.model.authModel.GenerateOtpForForgetPasswordRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.NewPasswordDTO
import com.example.seminarmanagementsystem.data.model.authModel.RegisterUserDTO
import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.data.model.authModel.UserRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.UserResponseDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpResponseDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface AuthRepo {
    // Register User
    suspend fun generateOtpForEmail(registerUserDTO: RegisterUserDTO): Response<ResponseDTO>

    suspend fun verifyOtpForEmail(verifyOtpRequestDTO: VerifyOtpRequestDTO): Response<VerifyOtpResponseDTO>

    suspend fun registerUser(authorization: String, userRequest: RegisterUserDTO): Response<ResponseDTO>

    // Login User
    suspend fun login(userRequest: UserRequestDTO): Response<UserResponseDTO>

    // Forget Password
    suspend fun generateOtpForExistingEmail(
        generateOtpForForgetPasswordRequestDTO: GenerateOtpForForgetPasswordRequestDTO
    ): Response<ResponseDTO>

    suspend fun verifyOtpForExistingEmail(
        verifyOtpRequestDTO: VerifyOtpRequestDTO
    ): Response<VerifyOtpResponseDTO>

    suspend fun changePasswordForExistingEmail(authorization: String, newPasswordDTO: NewPasswordDTO): Response<ResponseDTO>
}