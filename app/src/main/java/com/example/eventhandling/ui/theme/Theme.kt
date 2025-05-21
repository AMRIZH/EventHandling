package com.example.eventhandling.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = White,
    primaryContainer = PrimaryDarkBlue,
    onPrimaryContainer = Grey100,
    secondary = LightGreen,
    onSecondary = White,
    secondaryContainer = Cyan700,
    onSecondaryContainer = Grey100,
    tertiary = AccentOrange,
    onTertiary = White,
    background = Grey900,
    onBackground = Grey100,
    surface = Grey900,
    onSurface = Grey100
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryDarkBlue,
    onPrimary = White,
    primaryContainer = PrimaryBlue,
    onPrimaryContainer = White,
    secondary = LightGreen,
    onSecondary = White,
    secondaryContainer = Green100,
    onSecondaryContainer = Grey900,
    tertiary = AccentOrange,
    onTertiary = White,
    background = LightYellow,
    onBackground = Grey900,
    surface = Grey50,
    onSurface = Grey900
)

@Composable
fun EventHandlingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Modern way to handle status bar color
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val controller = WindowCompat.getInsetsController(window, view)
            controller.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}