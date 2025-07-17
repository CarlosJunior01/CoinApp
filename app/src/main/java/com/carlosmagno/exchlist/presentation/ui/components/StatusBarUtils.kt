package com.carlosmagno.exchlist.presentation.ui.components

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@SuppressLint("ContextCastToActivity")
@Composable
fun SetStatusBarColor(color: Color, darkIcons: Boolean = false) {
    val view = LocalView.current
    val activity = LocalContext.current as Activity

    SideEffect {
        val window = activity.window
        window.statusBarColor = color.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkIcons
    }
}