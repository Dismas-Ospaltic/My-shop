package com.ditech.myshop

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.st11.myshop.ui.theme.MyShopTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyShopTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyShopTheme {
//        Greeting("Android")
//    }
//}

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ditech.myshop.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ensure full-screen layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberAnimatedNavController()

//            val navBackStackEntry by navController.currentBackStackEntryAsState()
//            val currentRoute = navBackStackEntry?.destination?.route

//            // Define screens where the bottom bar should be hidden
//            val hideBottomBarScreens = listOf(Screen.ServiceDetail.route,Screen.Splash.route,Screen.Onboarding.route,Screen.SignUp.route,Screen.Login.route
//                ,Screen.ForgotPassword.route, Screen.NewUserScreen.route, Screen.CompleteProfile.route, Screen.Notification.route, Screen.Dashboard.route,
//                Screen.AddJobs.route)

            Scaffold(
//                bottomBar = {
////                    BottomNavigationBar(navController)
//                    if (currentRoute !in hideBottomBarScreens) {
//                        BottomNavigationBar(navController)
//                    }
//                },
//                floatingActionButton = {
//                    if (currentRoute == Screen.Home.route) { // Show FAB only on Home
//                        HomeFAB()
//                    }
//                }

            ) { paddingValues ->
                AppNavHost(navController, Modifier.padding(paddingValues))
            }
        }
    }
}


