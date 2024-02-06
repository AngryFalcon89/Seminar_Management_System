package com.example.seminarmanagementsystem.presentation.userAuth.forgetPassword

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.seminarmanagementsystem.presentation.common.OtpCard
import com.example.seminarmanagementsystem.presentation.utils.isStrongPassword

@Composable
fun ForgetPasswordScreen(
    viewModel: ForgetPasswordViewModel,
    onChangePassword: () -> Unit,
    onOtpEnter: () -> Unit
) {
    val forgetPasswordState by viewModel._resultValue.collectAsState()
    if (!forgetPasswordState.showOtpVerificationCard) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = forgetPasswordState.email,
                onValueChange = {
                    viewModel.resultValue.value = forgetPasswordState.copy(email = it)
                },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                )
            )

            OutlinedTextField(
                value = forgetPasswordState.newPassword,
                onValueChange = {
                    viewModel.resultValue.value = forgetPasswordState.copy(newPassword = it)
                },
                label = { Text("New Password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = PasswordVisualTransformation(),
                supportingText = {
                    if(!forgetPasswordState.isStrongPassword){
                        Text(text = "Enter a Strong password")
                    }
                }
            )

            // Button to trigger OTP generation
            Button(
                onClick = {
                    if(isStrongPassword(forgetPasswordState.newPassword)) {
                        viewModel.resultValue.value = forgetPasswordState.copy(isStrongPassword = true)
                        onChangePassword.invoke()
                    }else{
                        viewModel.resultValue.value = forgetPasswordState.copy(isStrongPassword = false)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                if (forgetPasswordState.isLoading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text("Generate OTP")
                }
            }
        }
    }

    // OTP Verification Card
    if (forgetPasswordState.showOtpVerificationCard) {
        OtpCard(
            otpText = forgetPasswordState.otp,
            onOtpTextChange = { value, otpInputFilled ->
                viewModel.resultValue.value = forgetPasswordState.copy(otp = value)
            },
            onSubmitClick = {
                onOtpEnter.invoke()
            },
            message = forgetPasswordState.message,
            isDone = forgetPasswordState.passwordChangeSuccefully
        )
    }

    // Show Result Message
    if (forgetPasswordState.message?.isNotEmpty() == true && !forgetPasswordState.showOtpVerificationCard) {
        Text(forgetPasswordState.message!!)
    }

    // Show Error Message
    if (forgetPasswordState.error.isNotEmpty() && !forgetPasswordState.showOtpVerificationCard) {
        Text("Error: ${forgetPasswordState.error}")
    }

}