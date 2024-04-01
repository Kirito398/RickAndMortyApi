package com.sir.entity.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkPalette
        else -> lightPalette
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primaryBackground.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(
        values = arrayOf(
            LocalColorProvider provides colorScheme,
            LocalTypographyProvider provides Typography,
            LocalDimensionsProvider provides DefaultDimensions
        ),
        content = content
    )
}

object AppTheme {
    val colors: Colors
        @Composable
        @ReadOnlyComposable
        get() = LocalColorProvider.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypographyProvider.current

    val dimensions: Dimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensionsProvider.current
}

val LocalColorProvider = staticCompositionLocalOf<Colors> {
    error("Default color not provided")
}

val LocalTypographyProvider = staticCompositionLocalOf<Typography> {
    error("Default typography not provided")
}

val LocalDimensionsProvider = staticCompositionLocalOf<Dimensions> {
    error("Default dimensions not provided")
}
