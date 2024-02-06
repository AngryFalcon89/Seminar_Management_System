package com.example.seminarmanagementsystem.presentation.bookData.IssueScreen.component

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
import com.example.seminarmanagementsystem.presentation.bookData.common.ArticleCardShimmerEffect
import com.example.seminarmanagementsystem.presentation.bookData.common.EmptyScreen
import com.example.seminarmanagementsystem.presentation.utils.dimens


@Composable
fun IssuedBookList(
    modifier: Modifier = Modifier,
    issuedBookList: LazyPagingItems<Book>,
    onClick:(Book) -> Unit
) {

    val handlePagingResult = handlePagingResult(issuedBookList)


    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(dimens.ExtraSmallPadding),
            contentPadding = PaddingValues(all = dimens.ExtraSmallPadding2)
        ) {
            items(
                count = issuedBookList.itemCount,
            ) {
                issuedBookList[it]?.let { bookDetails ->
                    IssuedBookCard(issuedBookDetails = bookDetails, onClick = {onClick(bookDetails)})
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
    Column(verticalArrangement = Arrangement.spacedBy(dimens.LargePadding)){
        repeat(10){
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = dimens.ExtraSmallPadding)
            )
        }
    }
}