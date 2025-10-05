package com.diwtech.myshop.utils
//
//import androidx.compose.ui.graphics.Color
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.graphics.luminance
//import com.google.accompanist.systemuicontroller.rememberSystemUiController
//

//@Composable
//fun DynamicStatusBar(backgroundColor: Color) {
//    val systemUiController = rememberSystemUiController()
//
//    // Determine if status bar icons should be dark or light
//    val useDarkIcons = backgroundColor.luminance() > 0.5
//
//    LaunchedEffect(backgroundColor) {
//        // Set transparent status bar with dynamic icon color
//        systemUiController.setStatusBarColor(
//            color = Color.Transparent,
//            darkIcons = useDarkIcons
//        )
//
//        // Always set navigation bar color to white with dark icons
//        systemUiController.setNavigationBarColor(
//            color = Color.White,
//            darkIcons = true
//        )
//    }
//}

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

@Composable
fun DynamicStatusBar(backgroundColor: Color) {
    val context = LocalContext.current
    val activity = context as? Activity ?: return

    LaunchedEffect(backgroundColor) {
        val window = activity.window

        // Enable edge-to-edge layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val useDarkIcons = backgroundColor.luminance() > 0.5

        // Control status & navigation bar icons via WindowInsetsControllerCompat
        val controller = WindowInsetsControllerCompat(window, window.decorView)
        controller.isAppearanceLightStatusBars = useDarkIcons // Status bar icons
        controller.isAppearanceLightNavigationBars = true    // Navigation bar icons

        // Modern Android handles bar colors automatically, no deprecated properties needed
    }
}
