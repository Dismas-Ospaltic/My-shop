package com.ditech.myshop.navigation



import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.compose.animation.*
import androidx.compose.ui.Modifier
import com.ditech.myshop.screens.AddProductScreen
import com.ditech.myshop.screens.AddSalesScreen
import com.ditech.myshop.screens.HomeScreen
import com.ditech.myshop.screens.ManageProductScreen
import com.ditech.myshop.screens.SalesReportsScreen
import com.ditech.myshop.screens.SingleProductSalesReportScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Settings : Screen("settings")

    object EditCalendar : Screen("editCalendar/{itemId}") {
        fun createRoute(itemId: String) = "eventDetail/$itemId"
    }

    object AddProduct : Screen("addProduct")

    object AddSales : Screen("addSales")

    object ManageProduct : Screen("manageProduct")

    object SaleReport : Screen("saleReport")

    object SingleSalesReport : Screen("singleSalesReport/{itemId}") {
        fun createRoute(itemId: String) = "singleSalesReport/$itemId"
    }


    object  CreditAuthor : Screen("CreditAuthor")
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
//        composable(Screen.Settings.route) { SettingScreen(navController) }
        composable(Screen.AddProduct.route) { AddProductScreen(navController) }
//        composable(Screen.CreditAuthor.route) {  CreditAuthorScreen(navController)   }
        composable(Screen.AddSales.route) { AddSalesScreen(navController) }
        composable(Screen.ManageProduct.route) { ManageProductScreen(navController) }
        composable(Screen.SaleReport.route) { SalesReportsScreen(navController) }

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