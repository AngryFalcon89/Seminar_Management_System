package com.example.seminarmanagementsystem.presentation.bookData.IssueScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.presentation.utils.FormatDate
import com.example.seminarmanagementsystem.presentation.utils.dimens
import com.example.seminarmanagementsystem.ui.theme.CoolGreen
import com.example.seminarmanagementsystem.ui.theme.PWhite
import com.example.seminarmanagementsystem.ui.theme.White
import com.example.seminarmanagementsystem.ui.theme.dmsansTypography


@Composable
fun IssuedBookCard(
    modifier: Modifier = Modifier,
    issuedBookDetails: Book,
    onClick: (() -> Unit)? = null
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimens.CardElevation
        ),
        modifier = modifier
            .background(White)
            .shadow(
                elevation = dimens.CardElevation,
                ambientColor = CoolGreen,
                spotColor = CoolGreen
            )
            .clickable { onClick?.invoke() },
        colors = CardDefaults.cardColors(PWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimens.SmallPadding)
                .sizeIn(minHeight = dimens.CardMinHeight)
        ) {
            Text(
                text = issuedBookDetails.Title.trim(),
                style = dmsansTypography.titleLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(6.dp))
            Spacer(modifier = Modifier.height(dimens.ExtraSmallPadding2))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (issuedBookDetails?.Author.toString().trim().isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            textDecoration = TextDecoration.Underline,
                            text = "Author",
                            style = dmsansTypography.titleSmall,
                            color = Color.Black
                        )
                        Text(
                            text = ": "+issuedBookDetails.Author,
                            style = dmsansTypography.titleMedium,
                            color =Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimens.ExtraSmallPadding2))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (issuedBookDetails?.Publisher.toString().trim().isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            textDecoration = TextDecoration.Underline,
                            text = "Publisher",
                            style = dmsansTypography.titleSmall,
                            color =  Color.Black
                        )
                        Text(
                            text = ": "+issuedBookDetails.Publisher.trim(),
                            style = dmsansTypography.titleMedium,
                            color = Color.Black

                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(dimens.ExtraSmallPadding2))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    textDecoration = TextDecoration.Underline,
                    text = "Issued to",
                    style = dmsansTypography.titleSmall,
                    color = Color.Black
                )
                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = ": "+issuedBookDetails.IssuedTo.name,
                    style = dmsansTypography.titleMedium,
                    color =Color.Black
                )
            }
            Spacer(modifier = Modifier.height(dimens.ExtraSmallPadding2))

            if (issuedBookDetails.IssuedTo.email?.toString() != null && issuedBookDetails.IssuedTo.email
                    .trim().isNotEmpty()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        textDecoration = TextDecoration.Underline,
                        text = "Email",
                        style = dmsansTypography.titleSmall,
                        color = Color.Black
                    )
                    Text(
                        text = ": "+issuedBookDetails.IssuedTo.email,
                        style = dmsansTypography.titleMedium,
                        color =Color.Black
                    )
                }
            }
            Spacer(modifier = Modifier.height(dimens.ExtraSmallPadding2))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    textDecoration = TextDecoration.Underline,
                    text = "Issued at",
                    style = dmsansTypography.titleSmall,
                    color = Color.Black
                )
                Text(
                    text = ": "+ FormatDate(issuedBookDetails.IssuedTo.issuedAt),
                    style = dmsansTypography.titleMedium,
                    color =Color.Black
                )
            }
            Spacer(modifier = Modifier.height(dimens.ExtraSmallPadding2))

            if (issuedBookDetails.IssuedTo.remarks?.toString() != null && issuedBookDetails.IssuedTo.remarks
                    .trim().isNotEmpty()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        textDecoration = TextDecoration.Underline,
                        text = "Remarks",
                        style = dmsansTypography.titleSmall,
                        color = Color.Black
                    )
                    Text(
                        text = ": "+issuedBookDetails.IssuedTo.remarks,
                        style = dmsansTypography.titleMedium,
                        color =Color.Black
                    )
                }
            }
        }
    }
}