package com.example.seminarmanagementsystem.presentation.bookData.homeScreen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.presentation.navGraph.Route
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.HomeImageSize
import com.example.seminarmanagementsystem.presentation.utils.dimens.LargePadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.MediumPadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.SmallPadding
import com.example.seminarmanagementsystem.ui.theme.GoodGreen

@Composable
fun HomeTopBar(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .background(
                color = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp),
                shape = RoundedCornerShape(
                    bottomEnd = LargePadding,
                    bottomStart = LargePadding
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
            Image(
                painter = painterResource(id = R.drawable.ic_amu),
                contentDescription = null,
                modifier = Modifier
                    .size(HomeImageSize)
                    .weight(1f),
                colorFilter = ColorFilter.tint(GoodGreen)
            )
            Spacer(modifier = Modifier.width(SmallPadding))
            IconButton(
                onClick = { navController.navigate(route = Route.SignupScreen.route) },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = MediumPadding)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_signup),
                    contentDescription = stringResource(id = R.string.add_admin),
                    modifier = Modifier
                )
            }
        }
        Spacer(modifier = Modifier.height(ExtraSmallPadding))
    }
}