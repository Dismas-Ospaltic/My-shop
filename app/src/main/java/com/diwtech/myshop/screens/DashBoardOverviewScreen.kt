package com.diwtech.myshop.screens





import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.diwtech.myshop.R
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.diwtech.myshop.viewmodel.GenSaleViewModel
import com.diwtech.myshop.viewmodel.ProductViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.BoxOpen
import compose.icons.fontawesomeicons.solid.Calendar
import compose.icons.fontawesomeicons.solid.ChartBar
import compose.icons.fontawesomeicons.solid.ChartPie
import compose.icons.fontawesomeicons.solid.ExclamationTriangle
import compose.icons.fontawesomeicons.solid.ShoppingCart
import org.koin.androidx.compose.koinViewModel
import java.time.YearMonth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoardOverviewScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    val customColor = colorResource(id = R.color.prussian_blue)

    val context = LocalContext.current
        val currentYearMonth = remember{ YearMonth.now().toString()} // "2025-05"

    val productViewModel: ProductViewModel = koinViewModel()
    val genSaleViewModel: GenSaleViewModel = koinViewModel()
    val totalMonthlySales by genSaleViewModel.totalMonthSales.collectAsState()
    val totalTodaySales by genSaleViewModel.totalTodaySales.collectAsState()
    val totalNoOfSaleToday by genSaleViewModel.totalNoOfSaleToday.collectAsState()
    val totalNoOfSaleThisMonth by genSaleViewModel.totalNoOfSaleThisMonth.collectAsState()
    val totalProduct by productViewModel.totalNoActiveProduct.collectAsState()
    val totalLowStockProduct by productViewModel.totalNoActiveLowStockProduct.collectAsState()

//
//    LaunchedEffect(Unit) {
//        genSaleViewModel.getMonthlyTotalSales(currentYearMonth)
//        genSaleViewModel.getNumberOfMonthlySales(currentYearMonth)
//    }


    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {
            // Back Button
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .background(
                        color = colorResource(id = R.color.prussian_blue),
                        shape = CircleShape
                    )
                    .align(Alignment.Start)
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.ArrowLeft,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = "Analytics",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.dark)
            )

            Text(
                text = "View summary of everything at a glance",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//
//
//
//
//                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OverviewCard(
                        title = "Total Sales Today",
                        value = totalTodaySales.toString(),
                        icon = FontAwesomeIcons.Solid.ChartPie,
                        backgroundColor = Color(0xFF9C27B0) // Purple
                    )
                    OverviewCard(
                        title = "Total Sales This Month",
                        value = totalMonthlySales.toString(),
                        icon = FontAwesomeIcons.Solid.ChartBar,
                        backgroundColor = Color(0xFF4CAF50) // Green
                    )
                    OverviewCard(
                        title = "No. of Sales This Month",
                        value = totalNoOfSaleThisMonth.toString(),
                        icon = FontAwesomeIcons.Solid.Calendar,
                        backgroundColor = Color(0xFF2196F3) // Blue
                    )
                    OverviewCard(
                        title = "No. of Sales Today",
                        value = totalNoOfSaleToday.toString(),
                        icon = FontAwesomeIcons.Solid.ShoppingCart,
                        backgroundColor = Color(0xFF00BCD4) // Cyan
                    )
                    OverviewCard(
                        title = "Total Products",
                        value = totalProduct.toString(),
                        icon = FontAwesomeIcons.Solid.BoxOpen,
                        backgroundColor = Color(0xFFFF9800) // Orange
                    )
                    OverviewCard(
                        title = "Low Stock Products",
                        value = totalLowStockProduct.toString(),
                        icon = FontAwesomeIcons.Solid.ExclamationTriangle,
                        backgroundColor = Color(0xFFE91E63) // Pink/Red
                    )
                }
            }
        }
    }




}

@Composable
fun OverviewCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        color = Color.White,
                        fontSize = 20.sp
                    )
                )
            }
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Color.White,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}









