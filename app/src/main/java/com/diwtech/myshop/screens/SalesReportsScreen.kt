package com.diwtech.myshop.screens



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diwtech.myshop.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diwtech.myshop.BannerAd
import com.diwtech.myshop.utils.DynamicStatusBar
import com.diwtech.myshop.utils.readableDate
import com.diwtech.myshop.viewmodel.GenSaleViewModel
import com.diwtech.myshop.viewmodel.ProductViewModel
import com.diwtech.myshop.viewmodel.SingleProductSaleViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.koin.androidx.compose.koinViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesReportsScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    DynamicStatusBar(colorResource(id = R.color.white))
    // ✅ Define states for search
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedNotes by remember { mutableStateOf("") }


    val productViewModel: ProductViewModel = koinViewModel()
    val genSaleViewModel: GenSaleViewModel = koinViewModel()
    val singleProductSaleViewModel: SingleProductSaleViewModel = koinViewModel()
     val dailyReports = genSaleViewModel.dailySalesReports.collectAsState()




    val context = LocalContext.current


    Scaffold(
        bottomBar = {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                // ✅ Show banner ad
                BannerAd(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                        .padding(4.dp) // optional
                )


            }
//            // ✅ Show banner ad
//            BannerAd(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(4.dp) // optional
//            )



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
                .background(colorResource(id = R.color.white))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
                        end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,

                        )
            ) {
            // Fixed-position Back Button
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .padding(16.dp)
                    .size(40.dp)
                    .background(
                        color = backgroundColor,
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

            Spacer(modifier = Modifier.height(8.dp)) // space between icon and content


            // Title
            Text(
                text = "Reports",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "View your Sales Reports here",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )



            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors(
                    containerColor = backgroundColor
//                    containerColor = Color(0xFFF2F4F7) // greyish
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {

                    if (dailyReports.value.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.data_notfound), // Replace with your image in res/drawable
                                    contentDescription = "No Data",
                                    modifier = Modifier.size(120.dp)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "No data Sales available!",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    } else {

                        val groupedReports = dailyReports.value.groupBy { it.date }

                        groupedReports.forEach { (date, reportsForDate) ->

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFF9F9F9)) // soft background
                                    .clickable {
                                        navController.navigate("singleSalesReport/$date")
                                    }
                                    .padding(16.dp)
                            ) {
                                // Date
                                Text(
                                    text = readableDate(date).toString(),
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = backgroundColor
                                    )
                                )

                                Spacer(Modifier.height(8.dp))

                                // Total Sales
                                val totalSales = reportsForDate.sumOf { it.total }
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        "Total Sales: $totalSales",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }

                                Spacer(Modifier.height(6.dp))

                                // List all payment types dynamically
                                reportsForDate.forEach { report ->
                                    Text(
                                        "${report.saleType}: ${report.total}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Spacer(Modifier.height(4.dp))
                                }
                            }

                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 12.dp),
                                thickness = 1.dp,
                                color = Color(0xFFDDDDDD) // subtle divider color
                            )
                        }

                    }


                }
            }

        }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun SalesReportsScreenPreview() {
    SalesReportsScreen(navController = rememberNavController())
}



