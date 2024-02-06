package com.example.seminarmanagementsystem.presentation.userAuth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seminarmanagementsystem.domain.useCases.appEntryUseCase.AppEntryUseCases
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.AuthUseCases
import com.example.seminarmanagementsystem.domain.useCases.authUseCases.LoginUseCase
import com.example.seminarmanagementsystem.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val appEntryUseCases: AppEntryUseCases
):ViewModel() {
    val loginResultValue = MutableStateFlow(LoginState())
    var _loginResultValue : StateFlow<LoginState> = loginResultValue

    fun login()=viewModelScope.launch(Dispatchers.IO){
        authUseCases.loginUseCase(userRequest = loginResultValue.value.userRequest).collect {
            when(it){
                is NetworkResult.Success ->{
                    loginResultValue.value = LoginState(message = it.data?.body()?.message)
                }
                is NetworkResult.Loading ->{
                    loginResultValue.value = LoginState(isLoading = true)
                }
                is NetworkResult.Error ->{
                    loginResultValue.value = LoginState(error = it.message?:"An Unexpected Error")
                }
            }
        }
    }
    fun saveAppEntry() {
        viewModelScope.launch{
            appEntryUseCases.saveAppEntry()
        }
    }
}