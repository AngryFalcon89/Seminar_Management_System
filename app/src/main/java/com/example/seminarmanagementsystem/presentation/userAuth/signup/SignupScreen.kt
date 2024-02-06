package com.example.seminarmanagementsystem.presentation.userAuth.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.seminarmanagementsystem.R

@Composable
fun SignupScreen(
    viewModel: SignUpViewModel,
    onRegisterClicked: () -> Unit,
    onVerifyOtpClicked: () -> Unit
) {
    val signupState by viewModel._signupResultValue.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Input Fields
        OutlinedTextField(
            value = signupState.name,
            onValueChange = {
                viewModel.signupResultValue.value =
                    signupState.copy(
                        name = it
                    )
            },
            label = { Text("Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = signupState.userName,
            onValueChange = {
                viewModel.signupResultValue.value =
                    signupState.copy(
                        userName = it
                    )
            },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = signupState.email,
            onValueChange = {
                viewModel.signupResultValue.value =
                    signupState.copy(
                        email = it
                    )
            },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )

        var passwordVisibility by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = signupState.password,
            onValueChange = {
                viewModel.signupResultValue.value =
                    signupState.copy(
                        password = it
                    )
            },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = painterResource(if (passwordVisibility) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                        contentDescription = null
                    )
                }
            }
        )

        // Button to trigger signup
        Button(
            onClick = {
                onRegisterClicked.invoke()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            if (signupState.isLoading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Text("Signup")
            }
        }

        // Show OTP Sent Message
        if (signupState.showOtpSentMessage) {
            Text("OTP sent to email")
        }

        // OTP Verification Card
        if (signupState.showOtpVerificationCard) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = signupState.otp,
                        onValueChange = {
                            viewModel.signupResultValue.value =
                                signupState.copy(
                                    otp = it
                                )
                        },
                        label = { Text("Enter OTP") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )

                    // Button to trigger OTP verification
                    Button(
                        onClick = {
                            onVerifyOtpClicked.invoke()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text("Verify OTP")
                    }
                }
            }
        }
    }
}
