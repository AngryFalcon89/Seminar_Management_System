package com.example.seminarmanagementsystem.presentation.bookData.IssueScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.presentation.bookData.IssueScreen.component.IssuedBookList
import com.example.seminarmanagementsystem.presentation.utils.dimens
import com.example.seminarmanagementsystem.presentation.utils.dimens.SmallPadding
import com.example.seminarmanagementsystem.ui.theme.dmsansTypography

@Composable
fun IssueScreen(
    modifier: Modifier = Modifier
        .fillMaxSize(),
    issuedBookList: LazyPagingItems<Book>,
    navigateToIssueDetails: (Book) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .background(
                    color = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp),
                    shape = RoundedCornerShape(
                        bottomEnd = dimens.LargePadding,
                        bottomStart = dimens.LargePadding
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(top = SmallPadding)
                    .statusBarsPadding()
            ) {
                Text(
                    text = stringResource(id = R.string.Issue),
                    style = dmsansTypography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(SmallPadding)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(dimens.ExtraSmallPadding))

        IssuedBookList(
            modifier = Modifier.padding(horizontal = dimens.MediumPadding),
            issuedBookList = issuedBookList,
            onClick = {
                navigateToIssueDetails(it)
            }
        )
    }
}