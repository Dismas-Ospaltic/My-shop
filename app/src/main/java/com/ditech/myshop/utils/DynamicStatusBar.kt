package com.ditech.myshop.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun DynamicStatusBar(backgroundColor: Color) {
    val systemUiController = rememberSystemUiController()

    // Determine if status bar icons should be dark or light
    val useDarkIcons = backgroundColor.luminance() > 0.5

    LaunchedEffect(backgroundColor) {
        // Set transparent status bar with dynamic icon color
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons
        )

        // Always set navigation bar color to white with dark icons
        systemUiController.setNavigationBarColor(
            color = Color.White,
            darkIcons = true
        )
    }
}