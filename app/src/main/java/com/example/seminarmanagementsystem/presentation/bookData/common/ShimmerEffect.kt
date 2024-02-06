package com.example.seminarmanagementsystem.presentation.bookData.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import com.example.seminarmanagementsystem.R
import com.example.seminarmanagementsystem.presentation.utils.dimens.ArticleCardSize
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraLargePadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.ExtraSmallPadding2
import com.example.seminarmanagementsystem.presentation.utils.dimens.LargePadding
import com.example.seminarmanagementsystem.presentation.utils.dimens.MediumPadding

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}


@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(horizontal = ExtraSmallPadding2)
            .height(ArticleCardSize)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ExtraLargePadding)
                .padding(horizontal = LargePadding)
                .shimmerEffect()
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(horizontal = LargePadding)
                    .height(MediumPadding)
                    .shimmerEffect()
            )

        }
    }
}
