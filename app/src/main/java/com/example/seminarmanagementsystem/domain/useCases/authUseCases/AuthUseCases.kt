package com.example.seminarmanagementsystem.domain.useCases.authUseCases

import com.example.seminarmanagementsystem.domain.useCases.authUseCases.forgetPasswordUseCase.*
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.signupUseCases.*

data class AuthUseCases(
    val loginUseCase: LoginUseCase,
    val generateOtpUseCase: GenerateOtpUseCase,
    val verifyOtpUseCase: VerifyOtpUseCase,
    val registerUserUseCase: RegisterUserUseCase,
    val forgetPasswordUseCase: ForgetPasswordUseCase,
    val changePasswordUseCase: ChangePasswordUseCase,
    val verifyOtpForExistingEmailUseCase: VerifyOtpForExistingEmailUseCase
)
