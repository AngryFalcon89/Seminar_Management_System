package com.example.seminarmanagementsystem.presentation.bookData.addScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.presentation.utils.dimens
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraLargePadding2
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.LargePadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.MediumPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.SmallPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.SmallPadding2
import com.example.seminarmanagementsystem.presentation.utils.dimens.TonalElevation
import com.example.seminarmanagementsystem.presentation.utils.dimens.TopBarHeight
import com.example.seminarmanagementsystem.ui.theme.DarkGrey
import com.example.seminarmanagementsystem.ui.theme.GoodGreen
import com.example.seminarmanagementsystem.ui.theme.Red
import com.example.seminarmanagementsystem.ui.theme.White
import com.example.seminarmanagementsystem.ui.theme.dmsansTypography
import com.example.seminarmanagementsystem.ui.theme.poppinsTypography
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    viewModel: AddScreenViewModel,
    onAddClicked: () -> Unit
) {
    val addState by viewModel._addResultValue.collectAsState()
    val calendarState = rememberSheetState()

    CalendarDialog(
        state = calendarState,
        config = CalendarConfig(
            monthSelection = false,
            yearSelection = true,
            style = CalendarStyle.WEEK
        ),
        selection = CalendarSelection.Date { date ->
            viewModel.addResultValue.value =
                addState.copy(
                    publishingYear = date.toString()
                )
        }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = MediumPadding, end = MediumPadding, top = TopBarHeight)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,

            ) {
            CustomTextField(
                text = addState.title,
                readOnly = false,
                onValueChange = {
                    viewModel.addResultValue.value =
                        addState.copy(
                            title = it.toString()
                        )
                },
                label = "Title",
                trailingIcon = {},
                isNecessary = true
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            CustomTextField(
                text = addState.author1,
                readOnly = false,
                onValueChange = {
                    viewModel.addResultValue.value =
                        addState.copy(
                            author1 = it.toString(),
                            author = it.toString()

                        )
                },
                label = "Author 1",
                trailingIcon = {},
                isNecessary = true
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            CustomTextField(
                text = addState.author2,
                readOnly = false,
                onValueChange = {
                    viewModel.addResultValue.value =
                        addState.copy(
                            author2 = it.toString()
                        )
                },
                label = "Author 2",
                trailingIcon = {}
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            CustomTextField(
                text = addState.author3,
                readOnly = false,
                onValueChange = {
                    viewModel.addResultValue.value =
                        addState.copy(
                            author3 = it.toString()
                        )
                },
                label = "Author 3",
                trailingIcon = {}
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                NumberTextField(
                    modifier = Modifier.weight(1f),
                    text = addState.id,
                    readOnly = false,
                    onValueChange = {
                        viewModel.addResultValue.value =
                            addState.copy(
                                id = it.toString()
                            )
                    },
                    label = "ID",
                    isNecessary = true
                )
                Spacer(modifier = Modifier.width(SmallPadding2))
                NumberTextField(
                    modifier = Modifier.weight(1f),
                    text = addState.accessionNumber,
                    readOnly = false,
                    onValueChange = {
                        viewModel.addResultValue.value =
                            addState.copy(
                                accessionNumber = it.toString()
                            )
                    },
                    label = "Accession Number"
                )
            }
            Spacer(modifier = Modifier.width(SmallPadding2))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                NumberTextField(
                    modifier = Modifier.weight(1f),
                    text = addState.malAccNumber,
                    readOnly = false,
                    onValueChange = {
                        viewModel.addResultValue.value =
                            addState.copy(
                                malAccNumber = it.toString()
                            )
                    },
                    label = "Mal Acc Number"
                )
                Spacer(modifier = Modifier.width(SmallPadding2))
                NumberTextField(
                    modifier = Modifier.weight(1f),
                    text = addState.edition,
                    readOnly = false,
                    onValueChange = {
                        viewModel.addResultValue.value =
                            addState.copy(
                                edition = it.toString()
                            )
                    },
                    label = "Edition"
                )
            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                CustomTextField(
                    modifier = Modifier.weight(1f),
                    text = addState.publisher,
                    readOnly = false,
                    onValueChange = {
                        viewModel.addResultValue.value =
                            addState.copy(
                                publisher = it
                            )
                    },
                    label = "Publisher",
                    trailingIcon = {},
                    isNecessary = true
                )
                Spacer(modifier = Modifier.width(SmallPadding2))
                CustomTextField(
                    modifier = Modifier.weight(1f),
                    text = addState.publishingYear,
                    readOnly = true,
                    onValueChange = {

                    },
                    label = "Pub. Year",
                    trailingIcon = {
                        IconButton(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            onClick = { calendarState.show() }) {
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "Publishing Year"
                            )
                        }
                    }
                )

            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            CustomTextField(
                text = addState.category1,
                readOnly = false,
                onValueChange = {
                    viewModel.addResultValue.value =
                        addState.copy(
                            category1 = it
                        )
                },
                label = "Category 1",
                trailingIcon = {}
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            CustomTextField(
                text = addState.category2,
                readOnly = false,
                onValueChange = {
                    viewModel.addResultValue.value =
                        addState.copy(
                            category2 = it
                        )
                },
                label = "Category 2",
                trailingIcon = {}
            )
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
            CustomTextField(
                text = addState.category3,
                readOnly = false,
                onValueChange = {
                    viewModel.addResultValue.value =
                        addState.copy(
                            category3 = it
                        )
                },
                label = "Category 3",
                trailingIcon = {}
            )
            Spacer(modifier = Modifier.height(SmallPadding))
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = addState.id.isNotEmpty() &&
                        addState.title.isNotEmpty() &&
                        addState.publisher.isNotEmpty() &&
                        addState.author.isNotEmpty(),
                shape = RoundedCornerShape(SmallPadding2),
                onClick = {
                    onAddClicked.invoke()
                }
            ) {
                if (addState.isLoading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Book")
                    Text("Add Book")
                }
            }
            Spacer(modifier = Modifier.height(SmallPadding))


            if (addState.message.isNotEmpty() && addState.showDialog) {
                MessageCard(
                    message = addState.message,
                    icon = Icons.Rounded.CheckCircle,
                    onDismissRequest = {
                        viewModel.addResultValue.value =
                            addState.copy(
                                showDialog = false
                            )
                    }
                )
            }

            if (addState.error.isNotEmpty() && addState.showDialog) {
                MessageCard(
                    message = addState.error,
                    icon = Icons.Rounded.Info,
                    onDismissRequest = { viewModel.addResultValue.value =
                        addState.copy(
                            showDialog = false
                        )
                    }
                )
            }
        }
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
                    .padding(top = dimens.SmallPadding)
                    .statusBarsPadding()
            ) {
                Text(
                    text = stringResource(id = R.string.add_books),
                    style = dmsansTypography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(dimens.SmallPadding)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MessageCardPreview(
) {
    MessageCard(message = "Added Successfully",
        icon = Icons.Rounded.Info,
        onDismissRequest = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageCard(
    message: String,
    icon: ImageVector,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest.invoke() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(horizontal = dimens.SmallPadding2)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = TonalElevation
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                            TonalElevation
                        )
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SmallPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(LargePadding))
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            tint = GoodGreen,
                            modifier = Modifier.size(ExtraLargePadding2)
                        )
                        Spacer(modifier = Modifier.height(SmallPadding))
                        Text(text = message, color = GoodGreen, textAlign = TextAlign.Justify)
                        Spacer(modifier = Modifier.height(SmallPadding2))
                        TextButton(
                            modifier = Modifier.align(Alignment.End),
                            onClick = { onDismissRequest.invoke() }) {
                            Text(
                                "OK",
                                color = GoodGreen
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    label: String,
    trailingIcon: @Composable () -> Unit,
    isNecessary: Boolean = false
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = label,
                        style = poppinsTypography.bodyMedium,
                        color = GoodGreen
                    )
                    if (isNecessary) {
                        Text(
                            text = "*",
                            style = poppinsTypography.bodyMedium,
                            color = Red
                        )
                    }
                }
            },
            trailingIcon = {
                trailingIcon()
            },
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = RoundedCornerShape(dimens.SmallPadding),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    TonalElevation
                ),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    TonalElevation
                ),
                disabledContainerColor = White,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    TonalElevation
                ),
                focusedBorderColor = GoodGreen,
                cursorColor = GoodGreen,
                focusedLabelColor = Color.Black,
                focusedTextColor = DarkGrey,
                unfocusedTextColor = DarkGrey,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            textStyle = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun NumberTextField(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    label: String,
    isNecessary: Boolean = false
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier,
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            label = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = label,
                        style = poppinsTypography.bodyMedium,
                        color = GoodGreen
                    )
                    if (isNecessary) {
                        Text(
                            text = "*",
                            style = poppinsTypography.bodyMedium,
                            color = Red
                        )
                    }
                }
            },
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = RoundedCornerShape(dimens.SmallPadding),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    TonalElevation
                ),
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    TonalElevation
                ),
                disabledContainerColor = White,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                    TonalElevation
                ),
                focusedBorderColor = GoodGreen,
                cursorColor = GoodGreen,
                focusedLabelColor = Color.Black,
                focusedTextColor = DarkGrey,
                unfocusedTextColor = DarkGrey,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            textStyle = MaterialTheme.typography.bodySmall
        )
    }
}
