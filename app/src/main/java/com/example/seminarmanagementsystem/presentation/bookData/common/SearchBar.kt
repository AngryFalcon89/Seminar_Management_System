package com.example.seminarmanagementsystem.presentation.bookData.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.presentation.utils.dimens.Elevation
import com.example.seminarmanagementsystem.presentation.utils.dimens.IconSize
import com.example.seminarmanagementsystem.presentation.utils.dimens.SmallPadding
import com.example.seminarmanagementsystem.ui.theme.DarkGrey
import com.example.seminarmanagementsystem.ui.theme.GoodGreen
import com.example.seminarmanagementsystem.ui.theme.Green
import com.example.seminarmanagementsystem.ui.theme.SeminarManagementSystemTheme
import com.example.seminarmanagementsystem.ui.theme.White
import com.example.seminarmanagementsystem.ui.theme.poppinsTypography

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    Box(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = onValueChange,
            readOnly = readOnly,
            label = {
                Text(
                    text = stringResource(id = R.string.search),
                    style = poppinsTypography.bodyMedium,
                    color = Green
                )
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(IconSize),
                )
            },
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search),
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = RoundedCornerShape(SmallPadding),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(Elevation),
                unfocusedContainerColor =MaterialTheme.colorScheme.surfaceColorAtElevation(Elevation),
                disabledContainerColor = White,
                unfocusedBorderColor = MaterialTheme.colorScheme.surfaceColorAtElevation(Elevation),
                focusedBorderColor = GoodGreen,
                cursorColor = GoodGreen,
                focusedLabelColor = Color.Black,
                focusedTextColor = DarkGrey,
                unfocusedTextColor = DarkGrey,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                }
            ),
            textStyle = MaterialTheme.typography.bodySmall
        )
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchBarPreview() {
    SeminarManagementSystemTheme {
        SearchBar(text = "", onValueChange = {}, readOnly = false) {
        }
    }
}