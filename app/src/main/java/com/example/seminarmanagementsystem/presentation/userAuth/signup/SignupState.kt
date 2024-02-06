package com.example.seminarmanagementsystem.presentation.userAuth.signup

import com.example.seminarmanagementsystem.data.model.authModel.RegisterUserDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO

data class SignupState(
    val showOtpSentMessage: Boolean = false,
    val showOtpVerificationCard: Boolean = false,
    val isLoading: Boolean = false,
    val message: String? = "",
    val error: String = "",
    val name: String = "",
    val userName: String = "",
    val email: String = "",
    val password: String = "",
    val otp: String = "",
    val authorizationString: String? = "",
    val verifyOtpRequest: VerifyOtpRequestDTO = VerifyOtpRequestDTO(
        email = "",
        otp = ""
    ),
    val registerUser: RegisterUserDTO = RegisterUserDTO(
        name = "",
        password = "",
        username = "",
        email = ""
    )
)
