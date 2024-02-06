package com.example.seminarmanagementsystem.presentation.bookData.updateBook

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
import com.example.seminarmanagementsystem.data.model.bookModel.Book

@Composable
fun UpdateScreen(
    viewModel: UpdateScreenViewModel,
    book : Book
) {
    val addState by viewModel._updateResultValue.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        TextField(
            value = addState.accessionNumber,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        accessionNumber = it
                    )
            },
            label = { Text("Accession Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = addState.author,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        author = it
                    )
            },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.id,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        id = it
                    )
            },
            label = { Text("ID") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = addState.author1,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        author1 = it
                    )
            },
            label = { Text("Author1") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.author2,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        author2 = it
                    )
            },
            label = { Text("Author2") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = addState.author3,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        author3 = it
                    )
            },
            label = { Text("Author3") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.category1,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        category1 = it
                    )
            },
            label = { Text("Category1") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = addState.category2,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        category2 = it
                    )
            },
            label = { Text("Category2") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.category3,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        category3 = it
                    )
            },
            label = { Text("Category3") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.edition,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        edition = it
                    )
            },
            label = { Text("Edition") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.malAccNumber,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        malAccNumber = it
                    )
            },
            label = { Text("Mal Acc Number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.title,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        title = it
                    )
            },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.publisher,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        publisher = it
                    )
            },
            label = { Text("Publisher") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = addState.publishingYear,
            onValueChange = {
                viewModel.updateResultValue.value =
                    addState.copy(
                        publishingYear = it
                    )
            },
            label = { Text("Publishing Year") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.updateBook()
            }
        ) {
            if (addState.isLoading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Issue Book")
                Text("Issue Book")
            }
        }

        if (addState.message.isNotEmpty()) {
            MessageCard(message = addState.message, icon = Icons.Default.Info)
        }

        if (addState.error.isNotEmpty()) {
            MessageCard(message = addState.error, icon = Icons.Default.Info, isError = true)
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
