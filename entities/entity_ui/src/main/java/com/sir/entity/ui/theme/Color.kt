package com.sir.entity.ui.theme

import androidx.compose.ui.graphics.Color

val WhiteSmoke = Color(0xFFF5F5F5)
val DarkGray = Color(0xFF272B33)
val Gray = Color(0xFF3c3e44)
val Orange = Color(0xFFFF9800)
data class Colors(
    val primaryBackground: Color,
    val primarySecondBackground: Color,
    val headerTextColor: Color,
    val primaryTextColor: Color,
    val primaryTintTextColor: Color,
)

val lightPalette = Colors(
    primaryBackground = DarkGray,
    primarySecondBackground = Gray,
    headerTextColor = WhiteSmoke,
    primaryTextColor = WhiteSmoke,
    primaryTintTextColor = Orange,
)

val darkPalette = lightPalette
