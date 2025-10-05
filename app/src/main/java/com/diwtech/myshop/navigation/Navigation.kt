package com.diwtech.myshop.navigation



//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable
//import androidx.compose.animation.*
//import androidx.compose.ui.Modifier
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.diwtech.myshop.screens.AboutAppScreen
import com.diwtech.myshop.screens.AddProductScreen
import com.diwtech.myshop.screens.AddSalesScreen
import com.diwtech.myshop.screens.DashBoardOverviewScreen
import com.diwtech.myshop.screens.HomeScreen
import com.diwtech.myshop.screens.InactiveProductScreen
import com.diwtech.myshop.screens.LowStockScreen
import com.diwtech.myshop.screens.ManageProductScreen
import com.diwtech.myshop.screens.SalesReportsScreen
import com.diwtech.myshop.screens.SettingScreen
import com.diwtech.myshop.screens.SingleProductSalesReportScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")

    object EditCalendar : Screen("editCalendar/{itemId}") {
        fun createRoute(itemId: String) = "eventDetail/$itemId"
    }

    object AddProduct : Screen("addProduct")

    object Dashboard : Screen("dashboard")

    object AddSales : Screen("addSales")

    object ManageProduct : Screen("manageProduct")

    object SaleReport : Screen("saleReport")

    object LowStock : Screen("lowStock")

    object InactiveProducts : Screen("inactiveProducts")

    object SingleSalesReport : Screen("singleSalesReport/{itemId}") {
        fun createRoute(itemId: String) = "singleSalesReport/$itemId"
    }


    object  AboutApp : Screen("aboutApp")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable    //canRequestAds: Boolean,
fun AppNavHost(navController: NavHostController, modifier: Modifier) {


//    AnimatedNavHost(
//        navController,
//        startDestination = Screen.Home.route,
//        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
//        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
//        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
//        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
//    ) {                                             //, canRequestAds
//        composable(Screen.Home.route) { HomeScreen(navController) }
//        composable(Screen.Settings.route) { SettingScreen(navController) }
//        composable(Screen.AddProduct.route) { AddProductScreen(navController) }
//        composable(Screen.AboutApp.route) { AboutAppScreen(navController) }
//        composable(Screen.AddSales.route) { AddSalesScreen(navController) }
//        composable(Screen.ManageProduct.route) { ManageProductScreen(navController) }
//        composable(Screen.SaleReport.route) { SalesReportsScreen(navController) }
//        composable(Screen.Dashboard.route) { DashBoardOverviewScreen(navController) }
//        composable(Screen.LowStock.route) { LowStockScreen(navController) }
//        composable(Screen.InactiveProducts.route) { InactiveProductScreen(navController) }
//        composable(Screen.SingleSalesReport.route) { backStackEntry ->
//            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
//            SingleProductSalesReportScreen(navController, itemId)
//        }
//
//
//    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            enterTransition = {
                fadeIn(animationSpec = tween(400)) +
                        scaleIn(initialScale = 0.85f, animationSpec = tween(400))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(300)) +
                        scaleOut(targetScale = 1.1f, animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(400)) +
                        scaleIn(initialScale = 1.1f, animationSpec = tween(400))
            },
            popExitTransition = {
                fadeOut(animationSpec = tween(300)) +
                        scaleOut(targetScale = 0.85f, animationSpec = tween(300))
            }
        ) { HomeScreen(navController) }

        composable(Screen.Settings.route) { SettingScreen(navController) }
        composable(Screen.AddProduct.route) { AddProductScreen(navController) }
        composable(Screen.AboutApp.route) { AboutAppScreen(navController) }
        composable(Screen.AddSales.route) { AddSalesScreen(navController) }
        composable(Screen.ManageProduct.route) { ManageProductScreen(navController) }
        composable(Screen.SaleReport.route) { SalesReportsScreen(navController) }
        composable(Screen.Dashboard.route) { DashBoardOverviewScreen(navController) }
        composable(Screen.LowStock.route) { LowStockScreen(navController) }
        composable(Screen.InactiveProducts.route) { InactiveProductScreen(navController) }

        composable(
            route = Screen.SingleSalesReport.route
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
            SingleProductSalesReportScreen(navController, itemId)
        }
    }

//    This gives you:
//
//    Enter: zooms in from 85% size while fading in.
//
//    Exit: zooms out slightly (110%) while fading out.
//
//    Pop Enter/Exit: reversed zoom for back navigation.

}