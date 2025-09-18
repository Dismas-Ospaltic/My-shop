package com.diwtech.myshop.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.ui.Modifier
import com.diwtech.myshop.screens.AboutAppScreen
import com.diwtech.myshop.screens.AddProductScreen
import com.diwtech.myshop.screens.AddSalesScreen
import com.diwtech.myshop.screens.DashBoardOverviewScreen
import com.diwtech.myshop.screens.HomeScreen
import com.diwtech.myshop.screens.ManageProductScreen
import com.diwtech.myshop.screens.SalesReportsScreen
import com.diwtech.myshop.screens.SettingScreen
import com.diwtech.myshop.screens.SingleProductSalesReportScreen

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

    object SingleSalesReport : Screen("singleSalesReport/{itemId}") {
        fun createRoute(itemId: String) = "singleSalesReport/$itemId"
    }


    object  AboutApp : Screen("aboutApp")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier) {


    AnimatedNavHost(
        navController,
        startDestination = Screen.Home.route,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable(Screen.Home.route) { HomeScreen(navController) }
        composable(Screen.Settings.route) { SettingScreen(navController) }
        composable(Screen.AddProduct.route) { AddProductScreen(navController) }
        composable(Screen.AboutApp.route) { AboutAppScreen(navController) }
        composable(Screen.AddSales.route) { AddSalesScreen(navController) }
        composable(Screen.ManageProduct.route) { ManageProductScreen(navController) }
        composable(Screen.SaleReport.route) { SalesReportsScreen(navController) }
        composable(Screen.Dashboard.route) { DashBoardOverviewScreen(navController) }
        composable(Screen.SingleSalesReport.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
            SingleProductSalesReportScreen(navController, itemId)
        }
//        composable(Screen.EditCalendar.route) { backStackEntry ->
//            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
//            EditCalendarScreen(navController, itemId)
//        }







    }
}