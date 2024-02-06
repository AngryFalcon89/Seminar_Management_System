package com.example.seminarmanagementsystem.presentation.userAuth.signup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.data.model.authModel.RegisterUserDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO
import com.example.seminarmanagementsystem.data.model.bookModel.addBook.AddBookRequestDTO
import com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase.AppEntryUseCases
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.AuthUseCases
import com.example.seminarmanagementsystem.presentation.userAuth.login.LoginState
import com.example.seminarmanagementsystem.utils.Constants.TAG
import com.example.seminarmanagementsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {
    val signupResultValue = MutableStateFlow(SignupState())
    var _signupResultValue: StateFlow<SignupState> = signupResultValue

    fun generateOtp() = viewModelScope.launch(Dispatchers.IO) {
        val updatedState = signupResultValue.value.copy(
            email = signupResultValue.value.email,
            registerUser = RegisterUserDTO(
                name = signupResultValue.value.name,
                username = signupResultValue.value.userName,
                email = signupResultValue.value.email,
                password = signupResultValue.value.password
            )
        )
        signupResultValue.value = updatedState
        Log.d(TAG, "generateOtp: ${signupResultValue.value}")

        authUseCases.generateOtpUseCase(registerUserDTO = signupResultValue.value.registerUser)
            .collect {
                when (it) {
                    is NetworkResult.Success -> {
                        signupResultValue.value = signupResultValue.value.copy(
                            message = it.data?.body()?.message,
                            showOtpSentMessage = true,
                            showOtpVerificationCard = true
                        )
                        Log.d(TAG, "generateOtpAfterSuccess: ${signupResultValue.value}")
                    }

                    is NetworkResult.Loading -> {
                        signupResultValue.value = signupResultValue.value.copy(isLoading = true)
                    }

                    is NetworkResult.Error -> {
                        signupResultValue.value = signupResultValue.value.copy(
                            error = it.data?.body()?.message ?: "An Unexpected Error"
                        )
                    }
                }
            }
    }

    fun verifyOtp() = viewModelScope.launch(Dispatchers.IO) {
        signupResultValue.value =
            _signupResultValue.value.copy(
                verifyOtpRequest = VerifyOtpRequestDTO(
                    email = signupResultValue.value.email,
                    otp = signupResultValue.value.otp
                )
            )
        authUseCases.verifyOtpUseCase(verifyOtpRequestDTO = signupResultValue.value.verifyOtpRequest)
            .collect {
                when (it) {
                    is NetworkResult.Success -> {
                        val verifiedEmailToken = it.data?.body()?.verifiedEmailToken
                        signupResultValue.value =
                            signupResultValue.value.copy(
                                authorizationString = "Bearer $verifiedEmailToken"
                            )
                        signupResultValue.value =
                            signupResultValue.value.copy(
                                message = it.data?.body()?.message
                            )
                        registerUser()
                    }

                    is NetworkResult.Loading -> {
                        signupResultValue.value = signupResultValue.value.copy(isLoading = true)
                    }

                    is NetworkResult.Error -> {
                        signupResultValue.value =
                            signupResultValue.value.copy(
                                error = it.data?.body()?.message ?: "An Unexpected Error"
                            )
                    }
                }
            }
    }

    fun registerUser() = viewModelScope.launch(Dispatchers.IO) {
        signupResultValue.value.authorizationString?.let {
            authUseCases.registerUserUseCase(
                authorization = it,
                registerUserDTO = signupResultValue.value.registerUser
            ).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        signupResultValue.value =
                            signupResultValue.value.copy(message = it.data?.body()?.message)
                        Log.d(TAG, "registerUser: ${signupResultValue.value.message}")
                    }

                    is NetworkResult.Loading -> {
                        signupResultValue.value = signupResultValue.value.copy(isLoading = true)
                    }

                    is NetworkResult.Error -> {
                        signupResultValue.value =
                            signupResultValue.value.copy(
                                error = it.data?.body()?.message ?: "An Unexpected Error"
                            )
                    }
                }
            }
        }
    }
}