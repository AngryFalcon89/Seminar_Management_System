package com.example.seminarmanagementsystem.presentation.userAuth.forgetPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.data.model.authModel.GenerateOtpForForgetPasswordRequestDTO
import com.example.seminarmanagementsystem.data.model.authModel.NewPasswordDTO
import com.example.seminarmanagementsystem.data.model.authModel.VerifyOtpRequestDTO
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.AuthUseCases
import com.example.seminarmanagementsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgetPasswordViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
) : ViewModel() {
    val resultValue = MutableStateFlow(ForgetPasswordState())
    var _resultValue: StateFlow<ForgetPasswordState> = resultValue

    fun generateOtp() = viewModelScope.launch(Dispatchers.IO) {
        val updatedState = resultValue.value.copy(
            generateOtpRequest = GenerateOtpForForgetPasswordRequestDTO(
                email = resultValue.value.email
            )
        )
        resultValue.value = updatedState
        authUseCases.forgetPasswordUseCase(generateOtpForForgetPasswordRequestDTO = resultValue.value.generateOtpRequest)
            .collect {
                when (it) {
                    is NetworkResult.Success -> {
                        resultValue.value = resultValue.value.copy(
                            message = it.data?.body()?.message,
                            showOtpSentMessage = true,
                            showOtpVerificationCard = true
                        )
                    }

                    is NetworkResult.Loading -> {
                        resultValue.value = resultValue.value.copy(isLoading = true)
                    }

                    is NetworkResult.Error -> {
                        resultValue.value = resultValue.value.copy(
                            error = it.data?.body()?.message ?: "An Unexpected Error"
                        )
                    }
                }
            }
    }

    fun verifyOtp() = viewModelScope.launch(Dispatchers.IO) {
        resultValue.value =
            _resultValue.value.copy(
                verifyOtpRequest = VerifyOtpRequestDTO(
                    email = resultValue.value.email,
                    otp = resultValue.value.otp
                )
            )
        authUseCases.verifyOtpForExistingEmailUseCase(verifyOtpRequestDTO = resultValue.value.verifyOtpRequest)
            .collect {
                when (it) {
                    is NetworkResult.Success -> {
                        val verifiedEmailToken = it.data?.body()?.verifiedEmailToken
                        resultValue.value =
                            resultValue.value.copy(
                                isLoading = false,
                                authorizationString = "Bearer $verifiedEmailToken",
                                message = it.data?.body()?.message,
                                newPasswordRequest = NewPasswordDTO(
                                    email = resultValue.value.email,
                                    newPassword = resultValue.value.newPassword
                                )
                            )
                        registerUser()
                    }

                    is NetworkResult.Loading -> {
                        resultValue.value = resultValue.value.copy(isLoading = true)
                    }

                    is NetworkResult.Error -> {
                        resultValue.value =
                            resultValue.value.copy(
                                isLoading = false,
                                error = it.data?.body()?.message ?: "An Unexpected Error"
                            )
                    }
                }
            }
    }

    fun registerUser() = viewModelScope.launch(Dispatchers.IO) {
        resultValue.value.authorizationString?.let {
            authUseCases.changePasswordUseCase(
                authorization = it,
                newPasswordDTO = resultValue.value.newPasswordRequest
            ).collect {
                when (it) {
                    is NetworkResult.Success -> {
                        resultValue.value =
                            resultValue.value.copy(
                                message = it.data?.body()?.message,
                                isLoading = false,
                                passwordChangeSuccefully = true
                            )
                    }

                    is NetworkResult.Loading -> {
                        resultValue.value = resultValue.value.copy(isLoading = true)
                    }

                    is NetworkResult.Error -> {
                        resultValue.value =
                            resultValue.value.copy(
                                message = it.data?.body()?.message ?: "An Unexpected Error",
                                isLoading = false
                            )
                    }
                }
            }
        }
    }
}