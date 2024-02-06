package com.example.seminarmanagementsystem.presentation.bookData.detailsScreen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.seminarmanagementsystem.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onDeleteClick: ()->Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor =  colorResource(id = R.color.body),
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector =  Icons.Rounded.ArrowBack,
                    contentDescription = "Back",
                )
            }
        },
        actions = {

            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = "Delete"
                )
            }
        },
    )
}