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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diwtech.myshop.R
import androidx.compose.ui.unit.sp
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
fun SingleProductSalesReportScreen(navController: NavController, itemId: String?) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    DynamicStatusBar(colorResource(id = R.color.white))
    // ✅ Define states for search
    var isSearching by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedNotes by remember { mutableStateOf("") }

//    val singleSaleViewModel: SingleSaleViewModel = koinViewModel()
//    val singleProductSaleViewModel: SingleProductSaleViewModel = koinViewModel()
//
//    val saleReceipt by singleProductSaleViewModel.salesSummary.collectAsState()
//    val products by singleProductSaleViewModel.productsForReceipt.collectAsState()
//    val singleSale by singleSaleViewModel.singleSale.collectAsState()

    val productViewModel: ProductViewModel = koinViewModel()
    val genSaleViewModel: GenSaleViewModel = koinViewModel()
    val singleProductSaleViewModel: SingleProductSaleViewModel = koinViewModel()
    val dailyReports = genSaleViewModel.dailySalesReports.collectAsState()
    val saleReceipt = singleProductSaleViewModel.salesSummary.collectAsState().value
    val singleSale = genSaleViewModel.genSale.collectAsState()
    val products = singleProductSaleViewModel.productsForReceipt.collectAsState()

    val context = LocalContext.current



    LaunchedEffect(Unit) {

        if(itemId != null) {
            singleProductSaleViewModel.loadSalesByDate(itemId)
            genSaleViewModel.getGenSalesByDate(itemId)
        }
    }





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
                text = "Sales",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "View all Sales",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )


            Text(
                text = "On " + readableDate(itemId.toString()).toString(),
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)) // ✅ Clip first
                    .background(colorResource(id = R.color.prussian_blue))
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                textAlign = TextAlign.Center
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
//
                    if (saleReceipt.isEmpty()) {
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
                                    text = "No data available!",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    } else {

                        for (index in singleSale.value.indices) {
                            val sale = singleSale.value[index]

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp) // spacing between cards
                                    .clickable {
                                        selectedNotes = if (sale.description.isNullOrEmpty())
                                            "No additional sales Notes notes"
                                        else sale.description
                                        singleProductSaleViewModel.loadProductsByReceipt(sale.receipt)
                                        showSheet = true
                                    },
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White) // white background
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    // Receipt ID / Title
                                    Text(
                                        text = "Receipt: ${sale.receipt}",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.SemiBold,
                                            color = colorResource(id = R.color.non_photo_blue_2)
                                        )
                                    )

                                    // Sale type & Date
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "payment Method: ${sale.saleType}",
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Medium
                                            )
                                        )
                                    }

                                    HorizontalDivider(thickness = 1.dp, color = Color(0xFFE0E0E0))

                                    // Amounts
                                    Text(
                                        text = "Total Sales: ${sale.totalSale}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = "Total Paid: ${sale.totalPaid}",
                                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary)
                                    )
                                    Text(
                                        text = "Change: ${sale.change}",
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color(
                                                0xFF4CAF50
                                            )
                                        ) // green for positive
                                    )
                                }
                            }

                            // Optional divider between cards (remove if spacing is enough)
                            if (index < saleReceipt.lastIndex) {
                                Spacer(modifier = Modifier.height(4.dp))
                            }
                        }

                    }

                }
            }

        }
        }
    }



    if (showSheet) {
//        ModalBottomSheet(
//            onDismissRequest = { showSheet = false },
//            sheetState = sheetState,
//            containerColor = Color.White, // White background
//            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), // Rounded top
//            tonalElevation = 6.dp // Subtle shadow for depth
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp, vertical = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                // Title
//                Text(
//                    text = "Additional Notes",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,
//                    color = Color.Black
//                )
//
//                // Selected Note
//                Text(
//                    text = selectedNotes,
//                    fontSize = 16.sp,
//                    color = Color.Gray
//                )
//
//                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
//
//                // Items section
//                if (products.value.isNotEmpty()) {
//                    Text(
//                        text = "Items for selected receipt:",
//                        fontWeight = FontWeight.Medium,
//                        color = Color.Black
//                    )
//                    Column(
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        products.value.forEach {
//                            Card(
//                                modifier = Modifier.fillMaxWidth(),
//                                shape = RoundedCornerShape(12.dp),
//                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
//                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//                            ) {
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(12.dp),
//                                    horizontalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    Text(it.productName, fontWeight = FontWeight.SemiBold)
//                                    Text("Qty: ${it.quantity}")
//                                    Text("Total: ${it.total}")
//                                }
//                            }
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                // Close button
//                Button(
//                    onClick = { showSheet = false },
//                    modifier = Modifier.align(Alignment.End),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = backgroundColor, // Blue background
//                        contentColor = Color.White          // White text
//                    )
//                ) {
//                    Text("Close")
//                }
//
//            }
//        }

        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
//                    .fillMaxHeight(0.9f) // optional: limit sheet height
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()) // make column scrollable
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "Additional Sales Notes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                // Selected Note
                Text(
                    text = selectedNotes,
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

                // Items section
                if (products.value.isNotEmpty()) {
                    Text(
                        text = "Items for selected receipt:",
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        products.value.forEach {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
                                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(it.productName, fontWeight = FontWeight.SemiBold)
                                    Text("Qty: ${it.quantity}")
                                    Text("Total: ${it.total}")
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f)) // pushes button to bottom

                // Close button
                Button(
                    onClick = { showSheet = false },
                    modifier = Modifier.fillMaxWidth(), // full-width button
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = backgroundColor,
                        contentColor = Color.White
                    )
                ) {
                    Text("Close")
                }
            }
        }

    }









}






@Preview(showBackground = true)
@Composable
fun SingleProductSalesReportScreenPreview() {
    SingleProductSalesReportScreen(navController = rememberNavController(), itemId = "020")
}



