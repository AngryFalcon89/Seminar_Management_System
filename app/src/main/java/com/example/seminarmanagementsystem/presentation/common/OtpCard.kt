package com.example.seminarmanagementsystem.presentation.common

import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.seminarmanagementsystem.ui.theme.Grey
import com.example.seminarmanagementsystem.ui.theme.Red


@Composable
fun OtpCard(
    modifier: Modifier = Modifier,
    otpText: String,
    otpCount: Int = 6,
    onOtpTextChange: (String, Boolean) -> Unit,
    onSubmitClick: () -> Unit,
    message: String?,
    isDone: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!isDone) {
                    if (message != null) {
                        Text(message, style = MaterialTheme.typography.titleMedium)
                    }
                    Text("Enter OTP", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = TextFieldValue(otpText, selection = TextRange(otpText.length)),
                        onValueChange = {
                            if (it.text.length <= otpCount) {
                                onOtpTextChange.invoke(it.text, it.text.length == otpCount)
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        decorationBox = {
                            Row(horizontalArrangement = Arrangement.Center) {
                                repeat(otpCount) { index ->
                                    CharView(
                                        index = index,
                                        text = otpText
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { onSubmitClick.invoke() }) {
                        Text("Submit OTP")
                    }
                } else {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle, contentDescription = "Success",
                        modifier = Modifier.size(60.dp), tint = Red
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    if (message != null) {
                        Text(text = message)
                    }
                }
            }
        }
    }
}


@Composable
private fun CharView(
    index: Int,
    text: String
) {
    val isFocused = text.length == index
    val char = when {
        index >= text.length -> ""
        else -> text[index].toString()
    }
    Text(
        modifier = Modifier
            .width(40.dp)
            .border(
                1.dp, when {
                    isFocused -> Color.Black
                    else -> Grey
                }, RoundedCornerShape(8.dp)
            )
            .padding(2.dp),
        text = char,
        style = MaterialTheme.typography.headlineMedium,
        color = if (isFocused) {
            Color.Black
        } else {
            Color.Black
        },
        textAlign = TextAlign.Center
    )
}