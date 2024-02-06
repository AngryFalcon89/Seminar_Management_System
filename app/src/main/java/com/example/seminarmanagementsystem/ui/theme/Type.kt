package com.example.seminarmanagementsystem.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.seminarmanagementsystem.R

val Typography = Typography(
    bodyLarge = TextStyle(              //body1
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val aileron_heavy = FontFamily(
    Font(R.font.aileron_heavy)
)
val aileron_bold = FontFamily(
    Font(R.font.aileron_bold)
)
val aileron_regular = FontFamily(
    Font(R.font.aileron_regular)
)
val aileron_thin = FontFamily(
    Font(R.font.aileron_thin)
)
val aileron_ultralight = FontFamily(
    Font(R.font.aileron_ultralight)
)
val aileron_light = FontFamily(
    Font(R.font.aileron_light)
)


val aileronTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = aileron_heavy,
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp
    ),
    titleSmall = TextStyle(
        fontFamily = aileron_light,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    titleMedium = TextStyle(
        fontFamily = aileron_light,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        letterSpacing = 1.sp
    )

)

val dmsans = FontFamily(
    Font(R.font.dmsans_regular, FontWeight.Medium)
)

val dmsansTypography = Typography(
    labelLarge = TextStyle(         //h1
        fontFamily = dmsans,
        fontWeight = FontWeight.Medium,
        fontSize = 35.sp
    ),
    titleLarge = TextStyle(         //h1
        fontFamily = dmsans,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(            //h3
        fontFamily = dmsans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    titleSmall = TextStyle(             //h2
        fontFamily = dmsans,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)

val poppins = FontFamily(
    Font(R.font.poppins_light, FontWeight.ExtraLight)
)

val poppinsTypography = Typography(
    titleSmall = TextStyle(             //h2
        fontFamily = poppins,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 12.sp
    )
)
