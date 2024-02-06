package com.example.seminarmanagementsystem.presentation.bookData.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.presentation.bookData.common.ArticlesList
import com.example.seminarmanagementsystem.presentation.bookData.homeScreen.component.HomeTopBar
import com.example.seminarmanagementsystem.presentation.utils.dimens.MediumPadding

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    navController: NavController,
    bookList: LazyPagingItems<Book>,
    navigateToDetails: (Book) -> Unit
) {
    Column() {
        HomeTopBar(navController = navController)
        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding),
            bookList = bookList,
            onClick = {
                navigateToDetails(it)
            }
        )
    }
}
