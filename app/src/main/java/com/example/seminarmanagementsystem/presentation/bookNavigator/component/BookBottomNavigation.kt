package com.example.seminarmanagementsystem.presentation.bookNavigator.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.IconSize
import com.example.seminarmanagementsystem.presentation.utils.dimens.LargePadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.TonalElevation
import com.example.seminarmanagementsystem.ui.theme.SeminarManagementSystemTheme


@Composable
fun BookBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(
                    topStart = LargePadding,
                    topEnd = LargePadding
                )
            ),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = TonalElevation
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(IconSize),
                        )
                        Spacer(modifier = Modifier.height(ExtraSmallPadding))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                ),
            )
        }
    }
}

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)

@Preview
@Composable
fun NewsBottomNavigationPreview() {
    SeminarManagementSystemTheme() {
        BookBottomNavigation(items = listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_book, text = "Issue"),
        ), selectedItem = 0, onItemClick = {})
    }
}