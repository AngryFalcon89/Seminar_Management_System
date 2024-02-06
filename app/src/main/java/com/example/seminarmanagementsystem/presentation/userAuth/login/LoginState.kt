package com.example.seminarmanagementsystem.presentation.userAuth.login

import com.example.seminarmanagementsystem.data.model.authModel.UserRequestDTO

data class LoginState(
    val isLoading : Boolean = false,
    val message : String? = "",
    val error : String = "",
    val emailOrUsername : String = "",
    val password : String = "",
    val userRequest: UserRequestDTO = UserRequestDTO(
        emailOrUsername = "",
        password = ""
    )
)
