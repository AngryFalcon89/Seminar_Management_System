package com.example.seminarmanagementsystem.presentation.bookData.homeScreen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.seminarmanagementsystem.data.model.bookModel.Book
import com.example.seminarmanagementsystem.data.model.bookModel.issueBook.IssuedTo
import com.example.seminarmanagementsystem.presentation.utils.dimens
import com.example.seminarmanagementsystem.presentation.utils.dimens.CardElevation
import com.example.seminarmanagementsystem.presentation.utils.dimens.CardMinHeight
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding2
import com.example.seminarmanagementsystem.presentation.utils.dimens.SmallPadding
import com.example.seminarmanagementsystem.ui.theme.CoolGreen
import com.example.seminarmanagementsystem.ui.theme.Grey
import com.example.seminarmanagementsystem.ui.theme.PWhite
import com.example.seminarmanagementsystem.ui.theme.White
import com.example.seminarmanagementsystem.ui.theme.dmsansTypography

import androidx.compose.foundation.horizontalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight


@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    bookDetails: Book,
    onClick: (() -> Unit)? = null
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = CardElevation
        ),
        modifier = modifier
            .background(White)
            .shadow(
                elevation = CardElevation,
                ambientColor = CoolGreen,
                spotColor = CoolGreen
            )
            .clickable { onClick?.invoke() },
        colors = CardDefaults.cardColors(PWhite)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallPadding)
                .sizeIn(minHeight = CardMinHeight)
        ) {
            Text(
                fontWeight = FontWeight.SemiBold,
                text = bookDetails.Title.trim(),
                style = dmsansTypography.titleLarge,
                color = if (bookDetails.Book_Status) {
                    Color.Black
                } else {
                    Grey
                }
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (bookDetails?.Author.toString().trim().isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Author: ",
                            style = dmsansTypography.titleMedium,
                            color = if (bookDetails.Book_Status) {
                                Color.Black
                            } else {
                                Grey
                            }
                        )
                        Text(
                            fontWeight = FontWeight.SemiBold,
                            text = bookDetails.Author,
                            style = dmsansTypography.titleMedium,
                            color = if (bookDetails.Book_Status) {
                                Color.Black
                            } else {
                                Grey
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (bookDetails?.Publisher.toString().trim().isNotEmpty()) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Publisher: ",
                            style = dmsansTypography.titleMedium,
                            color = if (bookDetails.Book_Status) {
                                Color.Black
                            } else {
                                Grey
                            }
                        )
                        Text(
                            fontWeight = FontWeight.SemiBold,
                            text = bookDetails.Publisher.trim(),
                            style = dmsansTypography.titleMedium,
                            color = if (bookDetails.Book_Status) {
                                Color.Black
                            } else {
                                Grey
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.Start
            ) {
                AssistChip(
                    modifier = Modifier.padding(end = ExtraSmallPadding),
                    onClick = {},
                    label = {
                        Text(
                            text = "Id. " + bookDetails.ID.toString(),
                            style = dmsansTypography.titleSmall,
                            color = if (bookDetails.Book_Status) {
                                Color.Black
                            } else {
                                Grey
                            }
                        )
                    }
                )
                if (bookDetails?.Edition!=null && bookDetails?.Edition.toString().trim().isNotEmpty()) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                text = "Edition: " + bookDetails.Edition.trim(),
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.Publishing_Year != null) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                text = "Publishing Year: " + bookDetails.Publishing_Year,
                                style = dmsansTypography.titleMedium,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.Category1!=null && bookDetails.Category1.isNotEmpty()) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                bookDetails.Category1,
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )

                }
                if (bookDetails?.Category2!=null && bookDetails.Category2.isNotEmpty()) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                bookDetails.Category2,
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.Category3!=null && bookDetails.Category3.isNotEmpty()) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                bookDetails.Category3,
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.Author1!=null && bookDetails.Author1.trim().isNotEmpty()) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                bookDetails.Author1.trim(),
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.Author2!=null && bookDetails.Author2.trim().isNotEmpty()) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                bookDetails.Author2.trim(),
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.Author3!=null && bookDetails.Author3.trim().isNotEmpty()) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                bookDetails.Author3.trim(),
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.Accession_Number != null) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                text = "Acc. No. " + bookDetails.Accession_Number.toString().trim(),
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
                if (bookDetails?.MAL_ACC_Number != null) {
                    AssistChip(
                        modifier = Modifier.padding(end = ExtraSmallPadding),
                        onClick = {},
                        label = {
                            Text(
                                text = "Mal. Acc. No. " + bookDetails.MAL_ACC_Number.toString()
                                    .trim(),
                                style = dmsansTypography.titleSmall,
                                color = if (bookDetails.Book_Status) {
                                    Color.Black
                                } else {
                                    Grey
                                }
                            )
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ItemCardPreview() {
    val sampleBook = Book(
        Accession_Number = 123,
        Author = "Sample Author",
        Author1 = "Author 1",
        Author2 = "Author 2",
        Author3 = "Author 3",
        Book_Status = true,
        Category1 = "Category1",
        Category2 = "Category2",
        Category3 = "Category3",
        Edition = "Sample Edition",
        ID = 456,
        IssuedTo = IssuedTo(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        ), // Assuming IssuedTo is another data class
        MAL_ACC_Number = 789,
        Publisher = "Sample Publisher",
        Publishing_Year = "2022",
        Title = "Sample Title",
        _id = "sample_id",
        updatedAt = "2022-01-01"
    )

    ItemCard(
        modifier = Modifier.fillMaxWidth(),
        bookDetails = sampleBook,
        onClick = { /* Handle click action */ }
    )
}
