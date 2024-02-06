package com.example.seminarmanagementsystem.presentation.bookData.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.presentation.bookData.homeScreen.component.ItemCard
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding2
import com.example.seminarmanagementsystem.presentation.utils.dimens.LargePadding


@Composable
fun ArticlesList(
    modifier: Modifier = Modifier,
    bookList: LazyPagingItems<Book>,
    onClick:(Book) -> Unit
) {

    val handlePagingResult = handlePagingResult(bookList)


    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(ExtraSmallPadding),
            contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(
                count = bookList.itemCount,
            ) {
                bookList[it]?.let { bookDetails ->
                    ItemCard(bookDetails = bookDetails, onClick = {onClick(bookDetails)})
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(articles: LazyPagingItems<Book>): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(LargePadding)){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = ExtraSmallPadding)
            )
        }
    }
}