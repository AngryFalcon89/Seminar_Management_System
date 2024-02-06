package com.example.seminarmanagementsystem.presentation.bookData.issueTheBook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.seminarmanagementsystem.data.model.bookModel.IssueDTO

@Composable
fun IssueTheBookScreen(
    viewModel : IssueTheBookViewModel,
    onIssueClicked :()->Unit
) {
    val issueState by viewModel.issueResultValue.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        TextField(
            value = issueState.name,
            onValueChange = {
                viewModel.issueResultValue.value =
                    issueState.copy(
                        name = it
                    )
            },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = issueState.email,
            onValueChange = {
                viewModel.issueResultValue.value =
                    issueState.copy(
                        email = it
                    )
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = issueState.returnDate,
            onValueChange = {
                viewModel.issueResultValue.value =
                    issueState.copy(
                        returnDate = it
                    )
            },
            label = { Text("Return Date") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = issueState.remarks,
            onValueChange = {
                viewModel.issueResultValue.value =
                    issueState.copy(
                        remarks = it
                    )
            },
            label = { Text("Remarks") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onIssueClicked.invoke()
            }
        ) {
            if (issueState.isLoading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Update Book")
                Text("Update Book")
            }
        }

        if (issueState.message.isNotEmpty()) {
            MessageCard(message = issueState.message, icon = Icons.Default.Info)
        }

        if (issueState.error.isNotEmpty()) {
            MessageCard(message = issueState.error, icon = Icons.Default.Info, isError = true)
        }
    }
}

@Composable
fun MessageCard(message: String, icon: ImageVector, isError: Boolean = false) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = message, color = Color.White)
        }
    }
}
