package com.example.roomlistcomposeui.ui.theme

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    background = Black,
    primary = Green,
    error = DarkRed,
    surface = LightBlack,
    tertiary = pinkText
)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    primary = Green,
    error = LightRed,
    surface = Color.White,
    tertiary = blueText
)

@Composable
fun RoomListComposeUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

   val colorScheme = if (darkTheme) {
      DarkColorScheme
   } else {
      LightColorScheme
   }

   MaterialTheme(
       colorScheme = colorScheme,
       typography = Typography,
       content = content
   )
}