package com.example.seminarmanagementsystem.presentation.bookData.detailsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.presentation.bookData.detailsScreen.component.DetailsTopBar
import com.example.seminarmanagementsystem.presentation.utils.dimens.LargePadding

@Composable
fun DetailsScreen(
    book: Book,
    onDeleteClicked:() -> Unit,
    navigateUp: () -> Unit,
    onUpdateClicked: () -> Unit,
    onReturnClicked: () -> Unit,
    onIssueClicked: () -> Unit
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onDeleteClick = {onDeleteClicked.invoke()},
            onBackClick = navigateUp
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = LargePadding,
                end = LargePadding,
                top = LargePadding
            )
        ) {
            item {
                Text(
                    text = book.Title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                if(!book.Book_Status) {
                    Text(
                        text = book.IssuedTo.remarks ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.body
                        )
                    )
                    Text(
                        text = book.IssuedTo.name ?: "",
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(
                            id = R.color.body
                        )
                    )
                    Button(onClick = { onReturnClicked.invoke() }) {
                        Text(text = "Return the Book")
                    }
                }
                if(book.Book_Status){
                    Button(onClick = { onIssueClicked.invoke() }) {
                        Text(text = "Issue the Book")
                    }
                }
                Button(onClick = { onUpdateClicked.invoke()}) {
                    Text(text = "Update")
                }
            }
        }
    }
}
