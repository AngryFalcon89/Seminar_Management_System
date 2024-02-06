package com.example.seminarmanagementsystem.presentation.userAuth.forgetPassword

import com.example.seminarmanagementsystem.data.model.authModel.GenerateOtpForForgetPasswordRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.NewPasswordDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO

data class ForgetPasswordState (
    val showOtpSentMessage: Boolean = false,
    val showOtpVerificationCard: Boolean = false,
    val isLoading: Boolean = false,
    val message: String? = "",
    val error: String = "",
    val email: String = "",
    val newPassword: String = "",
    val otp: String = "",
    val authorizationString: String? = "",
    val passwordChangeSuccefully: Boolean = false,
    val isStrongPassword: Boolean = true,
    val verifyOtpRequest: VerifyOtpRequestDTO = VerifyOtpRequestDTO(
        email = "",
        otp = ""
    ),
    val generateOtpRequest: GenerateOtpForForgetPasswordRequestDTO = GenerateOtpForForgetPasswordRequestDTO(
        email = ""
    ),
    val newPasswordRequest: NewPasswordDTO = NewPasswordDTO(
        email = "",
        newPassword = ""
    )
)