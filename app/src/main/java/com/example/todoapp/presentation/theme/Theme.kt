package com.example.todoapp.presentation.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Gray900,
    onPrimary = White,
    primaryContainer = Gray300,
    onPrimaryContainer = Gray900,
    secondary = Gray700,
    onSecondary = White,
    secondaryContainer = Gray200,
    onSecondaryContainer = Gray900,
    tertiary = Gray600,
    onTertiary = White,
    tertiaryContainer = Gray200,
    onTertiaryContainer = Gray900,
    error = Gray700,
    onError = White,
    errorContainer = Gray300,
    onErrorContainer = Gray900,
    background = Gray50,
    onBackground = Gray900,
    surface = White,
    onSurface = Gray900,
    surfaceVariant = Gray100,
    onSurfaceVariant = Gray600,
    outline = Gray400,
    outlineVariant = Gray300,
    scrim = Black,
    inverseSurface = Gray800,
    inverseOnSurface = Gray100,
    inversePrimary = Gray300,
    surfaceDim = Gray200,
    surfaceBright = White,
    surfaceContainerLowest = White,
    surfaceContainerLow = Gray50,
    surfaceContainer = Gray100,
    surfaceContainerHigh = Gray200,
    surfaceContainerHighest = Gray300,
)

private val DarkColorScheme = darkColorScheme(
    primary = Gray200,
    onPrimary = Black,
    primaryContainer = Gray700,
    onPrimaryContainer = Gray200,
    secondary = Gray300,
    onSecondary = Black,
    secondaryContainer = Gray700,
    onSecondaryContainer = Gray300,
    tertiary = Gray400,
    onTertiary = Black,
    tertiaryContainer = Gray700,
    onTertiaryContainer = Gray300,
    error = Gray300,
    onError = Black,
    errorContainer = Gray700,
    onErrorContainer = Gray300,
    background = Gray900,
    onBackground = Gray200,
    surface = Gray800,
    onSurface = Gray200,
    surfaceVariant = Gray700,
    onSurfaceVariant = Gray400,
    outline = Gray600,
    outlineVariant = Gray700,
    scrim = Black,
    inverseSurface = Gray200,
    inverseOnSurface = Gray800,
    inversePrimary = Gray700,
    surfaceDim = Gray900,
    surfaceBright = Gray700,
    surfaceContainerLowest = Black,
    surfaceContainerLow = Gray800,
    surfaceContainer = Gray800,
    surfaceContainerHigh = Gray700,
    surfaceContainerHighest = Gray600,
)

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
