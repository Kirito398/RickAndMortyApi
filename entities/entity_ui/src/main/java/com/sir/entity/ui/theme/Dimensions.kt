package com.sir.entity.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val defaultPadding: Dp,
    val progressIndicatorSize: Dp,
    val progressIndicatorStrokeWidth: Dp,
    val spacerSize: Dp,
    val markerSize: Dp,
)

val DefaultDimensions = Dimensions(
    defaultPadding = 6.dp,
    progressIndicatorSize = 20.dp,
    progressIndicatorStrokeWidth = 2.dp,
    spacerSize = 8.dp,
    markerSize = 8.dp,
)
