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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diwtech.myshop.R
import androidx.compose.ui.unit.sp
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

//    val sales = listOf(
//        Sales("674774777883",  "2025-08-01", 590f, 500f, 90f),
//        Sales("674774777884",  "2025-08-02", 750f, 700f, 50f),
//        Sales("674774777885",  "2025-08-03", 620f, 620f, 0f),
//        Sales("674774777886",  "2025-08-04", 820f, 700f, 120f),
//        Sales("674774777887",  "2025-08-05", 400f, 400f, 0f),
//        Sales("674774777888",  "2025-08-06", 910f, 850f, 60f),
//        Sales("674774777889",  "2025-08-07", 660f, 600f, 60f),
//        Sales("674774777890",  "2025-08-08", 590f, 500f, 90f),
//        Sales("674774777891",  "2025-08-09", 720f, 650f, 70f),
//        Sales("674774777892",  "2025-08-10", 810f, 780f, 30f)
//    )

    LaunchedEffect(Unit) {

        if(itemId != null) {
            singleProductSaleViewModel.loadSalesByDate(itemId)
            genSaleViewModel.getGenSalesByDate(itemId)
        }
    }





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
//                    for (index in sales.indices) {
//                        for (index in saleReceipt.value.indices){
//                    items(sales) { sale ->
//                    for (index in saleReceipt){
//                        val sale = saleReceipt[index] // Access each book

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
                                    painter = painterResource(id = R.drawable.bag), // Replace with your image in res/drawable
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
                    }else{
                        // Iterate over sales when not empty
//                        for (index in singleSale.value.indices) {
//                            val sale = singleSale.value[index]
//
//                            Column(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .clip(RoundedCornerShape(12.dp))
//                                    .background(Color(0xFFF9F9F9)) // soft background
//                                    .clickable {
//                                        selectedNotes = if(sale.description.isNullOrEmpty()) "No additional notes" else sale.description
//                                        singleProductSaleViewModel.loadProductsByReceipt(sale.receipt)
//                                        showSheet = true
//                                    }
//                                    .padding(16.dp)
//                            ) {
//                                // Date
//                                Text(
//                                    text = sale.receipt,
//                                    style = MaterialTheme.typography.titleMedium.copy(
//                                        fontWeight = FontWeight.SemiBold,
//                                        color = MaterialTheme.colorScheme.primary
//                                    )
//                                )
//
//                                Spacer(Modifier.height(8.dp))
//
//                                // Quantities
//                                Row(
//                                    Modifier.fillMaxWidth(),
//                                    horizontalArrangement = Arrangement.SpaceBetween
//                                ) {
//                                    Text(
//                                        text = sale.receipt,
//                                        style = MaterialTheme.typography.bodyMedium.copy(
//                                            fontWeight = FontWeight.Medium
//                                        )
//                                    )
//                                }
//                                Spacer(Modifier.height(6.dp))
//                                Text(
//                                    text = "Sale Type: ${sale.saleType}",
//                                    style = MaterialTheme.typography.bodyMedium
//                                )
//                                Spacer(Modifier.height(4.dp))
//                                Text(
//                                    text = "on: ${sale.date}",
//                                    style = MaterialTheme.typography.bodyMedium
//                                )
//                                Spacer(Modifier.height(4.dp))
//                                Text(
//                                    "Total Sales: ${sale.totalSale.toString()}",
//                                    style = MaterialTheme.typography.bodyMedium
//                                )
//                                Spacer(Modifier.height(4.dp))
//                                Text(
//                                    "Total Paid: ${sale.totalPaid.toString()}",
//                                    style = MaterialTheme.typography.bodyMedium
//                                )
//                                Spacer(Modifier.height(4.dp))
//                                Text(
//                                    "Change: ${sale.change.toString()}",
//                                    style = MaterialTheme.typography.bodyMedium
//                                )
//
//                            }
//
//
//                            // Divider except after last item
//                            if (index < saleReceipt.lastIndex) {
//                                HorizontalDivider(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(vertical = 8.dp),
//                                    thickness = 1.dp,
//                                    color = Color(0xFFE0E0E0)
//                                )
//                            }
//                        }

                        for (index in singleSale.value.indices) {
                            val sale = singleSale.value[index]

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp) // spacing between cards
                                    .clickable {
                                        selectedNotes = if (sale.description.isNullOrEmpty())
                                            "No additional notes"
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
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    )

                                    // Sale type & Date
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Sale Type: ${sale.saleType}",
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                                        )
                                        Text(
                                            text = sale.date,
                                            style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
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
                                        style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF4CAF50)) // green for positive
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


//    if (showSheet) {
//        ModalBottomSheet(
//            onDismissRequest = { showSheet = false },
//            sheetState = sheetState
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                Text("Additional Notes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                Text(selectedNotes)
//                Spacer(modifier = Modifier.height(16.dp))
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//
//                    if (products.value.isNotEmpty()) {
//                        Text("Items for selected receipt:")
//                        products.value.forEach {
//                            Text("${it.productName} - Qty: ${it.quantity} - Total: ${it.total}")
//                        }
//                    }
//
//                }
//                Button(
//                    onClick = { showSheet = false },
//                    modifier = Modifier.align(Alignment.End)
//                ) {
//                    Text("Close")
//                }
//            }
//        }
//    }

    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            containerColor = Color.White, // White background
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp), // Rounded top
            tonalElevation = 6.dp // Subtle shadow for depth
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "Additional Notes",
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

                Divider(color = Color.LightGray, thickness = 1.dp)

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

                Spacer(modifier = Modifier.height(20.dp))

                // Close button
                Button(
                    onClick = { showSheet = false },
                    modifier = Modifier.align(Alignment.End),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = backgroundColor, // Blue background
                        contentColor = Color.White          // White text
                    )
                ) {
                    Text("Close")
                }

            }
        }
    }









}



// ✅ Local book model + list


//data class Sales(
//    val receipt: String,
//    val date: String,
//    val totalPaid: Float,
//    val totalSales: Float,
//    val change: Float,
//
//)








@Preview(showBackground = true)
@Composable
fun SingleProductSalesReportScreenPreview() {
    SingleProductSalesReportScreen(navController = rememberNavController(), itemId = "020")
}



