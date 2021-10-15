package com.brunobterra.notescompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = backgroundColor,
    secondary = secondaryColor,
    background = backgroundColor,
    surface = surfaceColor,
    onPrimary = onBackground,
    onSecondary = onSecondary,
    onBackground = onBackground,
)

private val LightColorPalette = lightColors(
    primary = backgroundColor,
    secondary = secondaryColor,
    background = backgroundColor,
    surface = surfaceColor,
    onPrimary = onBackground,
    onSecondary = onSecondary,
    onBackground = onBackground,
)

@Composable
fun NotesComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}