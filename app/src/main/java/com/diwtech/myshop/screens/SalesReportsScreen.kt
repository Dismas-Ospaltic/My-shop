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
import com.diwtech.myshop.utils.DynamicStatusBar
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

//    val singleSaleViewModel: SingleSaleViewModel = koinViewModel()
//    val dailyReports = singleSaleViewModel.dailySalesReports.collectAsState()
//

    val productViewModel: ProductViewModel = koinViewModel()
    val genSaleViewModel: GenSaleViewModel = koinViewModel()
    val singleProductSaleViewModel: SingleProductSaleViewModel = koinViewModel()
     val dailyReports = genSaleViewModel.dailySalesReports.collectAsState()




    val context = LocalContext.current

//    val reports = listOf(
//        Report("2025-08-01", 620, 680, 590, 710, 4800f),
//        Report("2025-08-02", 650, 710, 605, 720, 4950f),
//        Report("2025-08-03", 670, 725, 615, 730, 5100f),
//        Report("2025-08-04", 640, 690, 600, 700, 4700f),
//        Report("2025-08-05", 680, 740, 620, 740, 5200f),
//        Report("2025-08-06", 660, 715, 610, 720, 5050f),
//        Report("2025-08-07", 675, 735, 625, 735, 5150f),
//        Report("2025-08-08", 662, 700, 600, 700, 5000f),
//        Report("2025-08-09", 685, 750, 630, 750, 5300f),
//        Report("2025-08-10", 695, 760, 640, 760, 5400f)
//    )





    Scaffold(
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
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF2F4F7) // greyish
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
//                    for (index in reports.indices) {
//                        val report = reports[index] // Access each book

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
                                    painter = painterResource(id = R.drawable.shopping), // Replace with your image in res/drawable
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
                    }else{

                        for (index in dailyReports.value.indices) {
                            val report = dailyReports.value[index] // Access each book



                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(Color(0xFFF9F9F9)) // soft background
                                    .clickable {
//                                        navController.navigate(Screen.SingleSalesReport.route(report.date))
                                        navController.navigate("singleSalesReport/${report.date}")
                                    }
                                    .padding(16.dp)
                            ) {
                                // Date
                                Text(
                                    text = report.date,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                )

                                Spacer(Modifier.height(8.dp))

                                // Quantities
                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        "Totals Sales: ${report.total.toString()}",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                }
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    "Cash Payments: ${report.cash}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "Bank Payments: ${report.bank}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "M-pesa payments: ${report.mpesa}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "Other payments: ${report.other}",
                                    style = MaterialTheme.typography.bodyMedium
                                )

                            }

                            // Divider between items (except last one)
//                            if (inventoryUpdate != allInventoryUpdatesById.last()) {
                            if (index < dailyReports.value.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp),
                                    thickness = 1.dp,
                                    color = Color(0xFFDDDDDD) // subtle divider color
                                )
                            }
                            //////////////////////////////////









                        }
                    }





                }
            }


        }
    }



}



// ✅ Local book model + list
//data class Report(
//    val date: String,
//    val cash: Int,
//    val bank: Int,
//    val mpesa: Int,
//    val other: Int,
//    val total: Float
//)


@Preview(showBackground = true)
@Composable
fun SalesReportsScreenPreview() {
    SalesReportsScreen(navController = rememberNavController())
}



