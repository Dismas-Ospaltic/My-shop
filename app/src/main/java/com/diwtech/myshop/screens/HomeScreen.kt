package com.diwtech.myshop.screens

import com.diwtech.myshop.navigation.Screen
import com.diwtech.myshop.screens.components.DashboardFeatureCard
import com.diwtech.myshop.utils.DynamicStatusBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diwtech.myshop.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diwtech.myshop.BannerAdView
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ChartBar
import compose.icons.fontawesomeicons.solid.ChartPie
import compose.icons.fontawesomeicons.solid.Cog
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.Store


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    DynamicStatusBar(colorResource(id = R.color.white))

    Scaffold(
        bottomBar = {
            BannerAdView()
            // Bottom bar container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars) // ✅ Push above system nav bar
            ) {
                Button(
                    onClick = { /* TODO: Handle click */
                       navController.navigate(Screen.AddSales.route)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.Plus,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Add Sales",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    ) { paddingValues ->
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {




            // Title
            Text(
                text = "My Shop",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id=R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "Manage Sales in your panel",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )



            Spacer(modifier = Modifier.height(16.dp))

            // Feature cards
            DashboardFeatureCard(
                icon = FontAwesomeIcons.Solid.Store,
                iconBackgroundColor = colorResource(id = R.color.light_green),
                title = "Manage Products",
                subtitle = "add new product, update stock"
            ) { /* onClick */
                navController.navigate(Screen.ManageProduct.route)
            }

            DashboardFeatureCard(
                icon = FontAwesomeIcons.Solid.ChartBar,
                iconBackgroundColor = colorResource(id = R.color.crimson),
                title = "Reports",
                subtitle = "View Sales Reports"
            ) {
            navController.navigate(Screen.SaleReport.route)
            /* onClick */ }

            DashboardFeatureCard(
                icon = FontAwesomeIcons.Solid.ChartPie,
                iconBackgroundColor = colorResource(id = R.color.non_photo_blue_2),
                title = "Analytics",
                subtitle = "view your sales overview"
            ) {
            navController.navigate(Screen.Dashboard.route)
            /* onClick */ }

            DashboardFeatureCard(
                icon = FontAwesomeIcons.Solid.Cog,
                iconBackgroundColor = colorResource(id = R.color.cerulean),
                title = "Settings",
                subtitle = "Manage settings"
            ) {
            navController.navigate(Screen.Settings.route)
            /* onClick */ }


//            Column {
//                Text("Welcome to My POS App")
//                Spacer(Modifier.height(16.dp))
//                BannerAdView()
//            }

            BannerAdView()

        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}




