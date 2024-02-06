package com.example.seminarmanagementsystem.data.model.authModel

data class VerifyOtpRequestDTO(
    val email: String,
    val otp: String
)