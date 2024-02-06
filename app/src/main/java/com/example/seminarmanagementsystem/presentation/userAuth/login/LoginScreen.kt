package com.example.seminarmanagementsystem.presentation.userAuth.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.data.model.authModel.UserRequestDTO
import com.example.seminarmanagementsystem.presentation.userAuth.common.MyButton
import com.example.seminarmanagementsystem.ui.theme.DarkGrey
import com.example.seminarmanagementsystem.ui.theme.Green
import com.example.seminarmanagementsystem.ui.theme.Grey
import com.example.seminarmanagementsystem.ui.theme.MediumGrey
import com.example.seminarmanagementsystem.ui.theme.PWhite
import com.example.seminarmanagementsystem.ui.theme.White
import com.example.seminarmanagementsystem.ui.theme.aileronTypography
import com.example.seminarmanagementsystem.ui.theme.dmsansTypography

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginClicked: () -> Unit,
    onSkipClicked: () -> Unit,
    onForgetClicked: () -> Unit
) {
    val loginState by viewModel._loginResultValue.collectAsState()
    val focusManager = LocalFocusManager.current
    val isFocused = remember { mutableStateOf(false) }
    val isVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp, 0.dp, 36.dp, 0.dp)
                .background(White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_amu_icon),
                contentDescription = stringResource(id = R.string.amu),
                tint = Green,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = stringResource(id = R.string.login),
                style = aileronTypography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                text = stringResource(id = R.string.login_message),
                color = MediumGrey,
                style = aileronTypography.titleSmall
            )
            Spacer(modifier = Modifier.height(50.dp))
            OutlinedTextField(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    },
                value = loginState.emailOrUsername,
                onValueChange = {
                    viewModel.loginResultValue.value =
                        loginState.copy(
                            emailOrUsername = it,
                            userRequest = UserRequestDTO(
                                emailOrUsername = it,
                                password = loginState.password
                            )
                        )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.name),
                        style = aileronTypography.titleMedium,
                        fontSize = if (isFocused.value || loginState.emailOrUsername.isNotEmpty()) 12.sp else 15.sp,
                        color = if (isFocused.value || loginState.emailOrUsername.isNotEmpty()) Color.Black else Color.Gray,
                        modifier = Modifier.padding(start = if (isFocused.value || loginState.emailOrUsername.isNotEmpty()) 0.dp else 10.dp)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DarkGrey,
                    unfocusedTextColor = DarkGrey,
                    focusedContainerColor = Grey,
                    unfocusedContainerColor = Grey,
                    disabledContainerColor = Grey,
                    cursorColor = Green,
                    focusedBorderColor = Green,
                    unfocusedBorderColor = PWhite,
                    focusedLabelColor = Color.Black,
                ),
                textStyle = dmsansTypography.titleMedium,
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .onFocusChanged {
                        isFocused.value = it.isFocused
                    },
                visualTransformation =
                if (isVisible.value) VisualTransformation.None
                else PasswordVisualTransformation(),
                value = loginState.password,
                onValueChange = {
                    viewModel.loginResultValue.value =
                        loginState.copy(
                            password = it,
                            userRequest = UserRequestDTO(
                                emailOrUsername = loginState.emailOrUsername,
                                password = it
                            )
                        )
                },
                label = {
                    Text(
                        text =  stringResource(id = R.string.password),
                        style = aileronTypography.titleMedium,
                        fontSize = if (isFocused.value || loginState.password.isNotEmpty()) 12.sp else 15.sp,
                        color = if (isFocused.value || loginState.password.isNotEmpty()) Color.Black else Color.Gray,
                        modifier = Modifier.padding(start = if (isFocused.value || loginState.password.isNotEmpty()) 0.dp else 10.dp)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = DarkGrey,
                    unfocusedTextColor = DarkGrey,
                    focusedContainerColor = Grey,
                    unfocusedContainerColor = Grey,
                    disabledContainerColor = Grey,
                    cursorColor = Green,
                    focusedBorderColor = Green,
                    unfocusedBorderColor = PWhite,
                    focusedLabelColor = Color.Black,
                ),
                textStyle = dmsansTypography.titleMedium,
                shape = RoundedCornerShape(20.dp),
                trailingIcon = {
                    IconButton(onClick = { isVisible.value = !isVisible.value }) {
                        Icon(
                            painter = painterResource(if (isVisible.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off),
                            contentDescription = "visibility"
                        )
                    }
                }, // Use the passed trailingIcon composable
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                ClickableText(
                    text = AnnotatedString("Forgot Password?"),
                    onClick = {
                        onForgetClicked.invoke()
                    },
                    modifier = Modifier.padding(bottom = 20.dp),
                    style = TextStyle(
                        color = Green,
                        textDecoration = TextDecoration.Underline
                    )
                )
            }
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                MyButton(
                    text = stringResource(id = R.string.skip),
                    modifier = Modifier.fillMaxWidth(0.48f),
                    onClick = {
                        onSkipClicked.invoke()
                    }
                )
                Spacer(modifier = Modifier.width(20.dp))
                MyButton(
                    text = "Log in",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onLoginClicked.invoke()
                    }
                )
            }
            Spacer(modifier = Modifier.height(25.dp))
        }
    }

    // Show Toast on receiving a response
    if (loginState.message?.isNotEmpty() == true) {
        Toast.makeText(
            LocalContext.current,
            loginState.message,
            Toast.LENGTH_SHORT
        ).show()
    }
}