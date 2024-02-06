package com.example.seminarmanagementsystem.presentation.userAuth.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seminarmanagementsystem.ui.theme.Red
import com.example.seminarmanagementsystem.ui.theme.aileronTypography
import com.example.seminarmanagementsystem.ui.theme.dmsansTypography

@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    onClick:()->Unit,
    text : String
) {
    Button(
        modifier = modifier.height(50.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Red),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            style = dmsansTypography.titleLarge,
            fontSize = 16.sp
        )
    }
}