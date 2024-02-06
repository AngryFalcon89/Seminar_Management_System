package com.example.seminarmanagementsystem.data.apiService

import com.example.seminarmanagementsystem.data.model.authModel.ResponseDTO
import com.example.seminarmanagementsystem.data.model.authModel.RegisterUserDTO
import com.example.seminarmanagementsystem.data.model.authModel.UserRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.UserResponseDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpResponseDTO
import com.example.seminarmanagementsystem.data.model.authModel.GenerateOtpForForgetPasswordRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.NewPasswordDTO
import com.example.seminarmanagementsystem.data.model.bookModel.BooksDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

//--------------Authentication Service Start-----------------

    //-------------------Register User-----------------------
    @POST("auth/generate-otp-for-email")
    suspend fun generateOtpForEmail(
        @Body registerUserDTO: RegisterUserDTO
    ): Response<ResponseDTO>

    @POST("auth/verify-otp-for-email")
    suspend fun verifyOtpForEmail(
        @Body verifyOtpRequestDTO: VerifyOtpRequestDTO
    ): Response<VerifyOtpResponseDTO>

    @POST("auth/register-user")
    suspend fun registerUser(@Header("Authorization") authorization: String, @Body userRequest: RegisterUserDTO): Response<ResponseDTO>

    //---------------------Login User------------------------
    @POST("/auth/login")
    suspend fun login(@Body userRequest: UserRequestDTO): Response<UserResponseDTO>

    //-------------------Forget Password---------------------
    @POST("/auth/generate-otp-for-existing-email")
    suspend fun generateOtpForExistingEmail(
        @Body generateOtpForForgetPasswordRequestDTO: GenerateOtpForForgetPasswordRequestDTO
    ): Response<ResponseDTO>

    @POST("/auth/verify-otp-for-exsiting-email")
    suspend fun verifyOtpForExistingEmail(
        @Body verifyOtpRequestDTO: VerifyOtpRequestDTO
    ): Response<VerifyOtpResponseDTO>

    @POST("/auth/change-password-for-existing-email")
    suspend fun changePasswordForExistingEmail(@Header("Authorization") authorization: String, @Body newPasswordDTO: NewPasswordDTO): Response<ResponseDTO>

//--------------Authentication Service End-------------------

//-----------------Books Service Start-----------------------

    @GET("/books/")
    suspend fun getBookList(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): BooksDTO

    @GET("/books/")
    suspend fun searchBooks(
        @Query("search_query") searchQuery: String,
        @Query("page") page: Int
    ): BooksDTO

//------------------Books Service End------------------------

}