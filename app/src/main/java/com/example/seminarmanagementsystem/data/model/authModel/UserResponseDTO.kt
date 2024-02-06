package com.example.seminarmanagementsystem.data.model.authModel


data class UserResponseDTO(
    val authToken: String,
    val message: String,
    val status: String
)