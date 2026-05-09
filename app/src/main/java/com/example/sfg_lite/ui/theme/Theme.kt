package com.example.sfg_lite.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = GoldYellow,
    secondary = Yellow,
    tertiary = Red,
    background = ProDarkBackground,
    surface = ProDarkSurface,
    onPrimary = ProDarkBackground,
    onSecondary = ProDarkBackground,
    onTertiary = White,
    onBackground = White,
    onSurface = White
)

private val LightColorScheme = lightColorScheme(
    primary = GoldYellow,
    secondary = Yellow,
    tertiary = Red,
    background = White,
    surface = White,
    onPrimary = ProDarkBackground,
    onSecondary = ProDarkBackground,
    onTertiary = White,
    onBackground = ProDarkBackground,
    onSurface = ProDarkBackground
)

@Composable
fun SpectreFrameTheme(
    darkTheme: Boolean = true, // Force dark theme for the "Pro look"
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
