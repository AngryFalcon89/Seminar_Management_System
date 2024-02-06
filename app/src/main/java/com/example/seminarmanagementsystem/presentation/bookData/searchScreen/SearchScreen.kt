package com.example.seminarmanagementsystem.presentation.bookData.searchScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.presentation.bookData.common.ArticlesList
import com.example.seminarmanagementsystem.presentation.bookData.common.SearchBar
import com.example.seminarmanagementsystem.presentation.utils.dimens
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.LargePadding
import com.example.seminarmanagementsystem.ui.theme.GoodGreen

@Composable
fun SearchScreen(
    state: SearchState,
    event:(SearchEvent) -> Unit,
    navigateToDetails:(Book) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(dimens.SmallPadding))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = dimens.LargePadding)
                .fillMaxWidth(),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = {event(SearchEvent.UpdateSearchQuery(it))},
            onSearch = {event(SearchEvent.SearchNews)}
        )
        Spacer(modifier = Modifier.height(ExtraSmallPadding))
        if(!state.isSearched){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimens.MediumPadding)
            ) {
                Text(
                    text = "Search books based on Title, Author or Publisher...",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = GoodGreen
                )
            }

        }

        state?.books?.let {
            val books = it.collectAsLazyPagingItems()
            if(books.itemCount==0){
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = dimens.MediumPadding)
                ) {
                    Text(
                        text = "Nothing found!!!",
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = GoodGreen
                    )
                }
            }
            ArticlesList(
                modifier = Modifier.padding(horizontal = dimens.MediumPadding),
                bookList = books,
                onClick = {
                    navigateToDetails(it)
                }
            )
        }
    }
}
