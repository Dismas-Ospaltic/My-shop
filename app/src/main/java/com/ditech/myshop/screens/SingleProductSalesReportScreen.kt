package com.ditech.myshop.screens


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
import com.ditech.myshop.R
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ditech.myshop.utils.DynamicStatusBar
import com.ditech.myshop.viewmodel.GenSaleViewModel
import com.ditech.myshop.viewmodel.ProductViewModel
import com.ditech.myshop.viewmodel.SingleProductSaleViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ThumbsUp
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.InfoCircle
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.ShareAlt
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
    val singleSale by singleProductSaleViewModel.singleProduct.collectAsState()
    val products by singleProductSaleViewModel.productsForReceipt.collectAsState()

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

//        if(itemId != null) {
//            singleProductSaleViewModel.loadSalesByDate(itemId)
//            singleSaleViewModel.getSalesByDate(itemId)
//        }
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
                        for (index in singleSale.indices) {
                            val sale = singleSale[index]
                            // Book row
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        // Handle click here if needed
                                    }
                            ) {
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = sale.receipt,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Spacer(Modifier.height(4.dp))

                                Text(
                                    text = "Sale Type: ${sale.saleType}",
                                )
                                Spacer(Modifier.height(4.dp))

                                Text(
                                    text = "on: ${sale.date}",
                                )
                                Spacer(Modifier.height(4.dp))

                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        "Total Sales: ${sale.totalSale.toString()}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "Total Paid: ${sale.totalPaid.toString()}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    "Change: ${sale.change.toString()}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Spacer(Modifier.height(4.dp))




                                Row(
                                    Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    IconButton(
                                        onClick = {
                                            selectedNotes = sale.description
                                            singleProductSaleViewModel.loadProductsByReceipt(sale.receipt)
                                            showSheet = true
                                        },
                                        modifier = Modifier
                                            .size(56.dp) // total button size
                                            .clip(RoundedCornerShape(16.dp)) // round corners
                                    ) {
                                        Icon(
                                            imageVector = FontAwesomeIcons.Solid.InfoCircle,
                                            contentDescription = "Info",
                                            tint = colorResource(id = R.color.bittersweet),
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }
                            }

                            // Divider except after last item
                            if (index < saleReceipt.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp),
                                    thickness = 1.dp,
                                    color = Color(0xFFE0E0E0)
                                )
                            }
                        }
                    }

                }
            }


        }
    }


    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Additional Notes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(selectedNotes)
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {


//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//
//                            }
//                            .padding(12.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = FontAwesomeIcons.Solid.CircleNotch,
//                            contentDescription = "update",
//                            modifier = Modifier.size(20.dp)
//                        )
//                        Spacer(modifier = Modifier.width(12.dp))
//                        Text(text = "update Progress", fontSize = 16.sp)
//                    }
//
//                    // Edit Button
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
////                                showEditDialog = true
//                            }
//                            .padding(12.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            imageVector = FontAwesomeIcons.Solid.Pen,
//                            contentDescription = "Edit",
//                            modifier = Modifier.size(20.dp)
//                        )
//                        Spacer(modifier = Modifier.width(12.dp))
//                        Text(text = "Edit", fontSize = 16.sp)
//                    }

                    if (products.isNotEmpty()) {
                        Text("Items for selected receipt:")
                        products.forEach {
                            Text("${it.productName} - Qty: ${it.quantity} - Total: ${it.total}")
                        }
                    }

                }
//                Button(
//                    onClick = { showSheet = false },
//                    modifier = Modifier.align(Alignment.End)
//                ) {
//                    Text("Close")
//                }
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



