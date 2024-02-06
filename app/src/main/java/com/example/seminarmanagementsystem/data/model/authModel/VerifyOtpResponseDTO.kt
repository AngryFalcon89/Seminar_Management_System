package com.example.seminarmanagementsystem.data.model.authModel

data class VerifyOtpResponseDTO(
    val message: String,
    val status: String,
    val verifiedEmailToken: String
)