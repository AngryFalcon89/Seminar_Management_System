package com.example.seminarmanagementsystem.data.repository.authRepo

import android.util.Log
import com.example.seminarmanagementsystem.data.apiService.ApiService
import com.example.seminarmanagementsystem.data.apiService.AuthApiService
import com.example.seminarmanagementsystem.data.model.authModel.GenerateOtpForForgetPasswordRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.NewPasswordDTO
import com.example.seminarmanagementsystem.data.model.authModel.RegisterUserDTO
import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.data.model.authModel.UserRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.UserResponseDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpResponseDTO
import com.example.seminarmanagementsystem.domain.repository.authRepo.AuthRepo
import com.example.seminarmanagementsystem.domain.tokenPreference.TokenPreference
import com.example.seminarmanagementsystem.utils.Constants.TAG
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class AuthRepoImpl(
    private val apiService: ApiService,
    private val tokenPreference: TokenPreference
) : AuthRepo {
    // Register User
    override suspend fun generateOtpForEmail(registerUserDTO: RegisterUserDTO): Response<ResponseDTO> =
        apiService.generateOtpForEmail(registerUserDTO)

    override suspend fun verifyOtpForEmail(verifyOtpRequestDTO: VerifyOtpRequestDTO): Response<VerifyOtpResponseDTO> =
        apiService.verifyOtpForEmail(verifyOtpRequestDTO)

    override suspend fun registerUser( authorization: String, userRequest: RegisterUserDTO): Response<ResponseDTO> =
        apiService.registerUser(authorization, userRequest)

    // Login User
//    override suspend fun login(userRequest: UserRequestDTO): Response<UserResponseDTO> {
//        Log.d(TAG, "inside login repo impl: ${userRequest}")
//        val response = apiService.login(userRequest)
//        Log.d(TAG, "inside login repo impl: ${response.body()}")
//        if (response.isSuccessful) {
//            val userResponse = response.body()
//            userResponse?.authToken?.let {
//                // Save authToken in PreferencesDataStore
//                tokenPreference.saveToken(it)
//            }
//        }
//        return response
//    }
    override suspend fun login(userRequest: UserRequestDTO): Response<UserResponseDTO> {
        try {
            Log.d(TAG, "inside login repo impl: ${userRequest}")
            val response = apiService.login(userRequest)
            Log.d(TAG, "inside login repo impl: ${response.body()}")

            if (response.isSuccessful) {
                val userResponse = response.body()
                userResponse?.authToken?.let {
                    // Save authToken in PreferencesDataStore
                    tokenPreference.saveToken(it)
                }
            }

            return response
        } catch (e: Exception) {
            // Handle exceptions, log them, or perform any necessary actions
            e.printStackTrace()
            Log.e(TAG, "Exception during login: ${e.message}", e)
            // You can throw or return a custom response here if needed
            throw e
        }
    }


    // Forget Password
    override suspend fun generateOtpForExistingEmail(
        generateOtpForForgetPasswordRequestDTO: GenerateOtpForForgetPasswordRequestDTO
    ): Response<ResponseDTO> =
        apiService.generateOtpForExistingEmail(generateOtpForForgetPasswordRequestDTO)

    override suspend fun verifyOtpForExistingEmail(
        verifyOtpRequestDTO: VerifyOtpRequestDTO
    ): Response<VerifyOtpResponseDTO> =
        apiService.verifyOtpForExistingEmail(verifyOtpRequestDTO)

    override suspend fun changePasswordForExistingEmail(authorization: String, newPasswordDTO: NewPasswordDTO): Response<ResponseDTO> =
        apiService.changePasswordForExistingEmail(authorization, newPasswordDTO)

}