package com.diwtech.myshop.screens



import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.diwtech.myshop.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diwtech.myshop.BannerAd
import com.diwtech.myshop.navigation.Screen
import com.diwtech.myshop.screens.components.EditProductPopUp
import com.diwtech.myshop.screens.components.StockHistPopUp
import com.diwtech.myshop.screens.components.UpdateStockPop
import com.diwtech.myshop.utils.DynamicStatusBar
import com.diwtech.myshop.viewmodel.ProductViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ArrowAltCircleLeft
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.CartArrowDown
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.ClipboardList
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.PlusCircle
import compose.icons.fontawesomeicons.solid.Search
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InactiveProductScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    DynamicStatusBar(colorResource(id = R.color.white))
    val searchQuery = remember { mutableStateOf("") }

    val productViewModel: ProductViewModel = koinViewModel()
    val activeProducts by productViewModel.activeProducts.collectAsState()
    val inactiveProducts by productViewModel.inactiveProducts.collectAsState()


    var selectedProductId by remember { mutableStateOf<String?>(null) }
    var selectedProductCode by remember { mutableStateOf<String?>(null) }
    var selectedProductName by remember { mutableStateOf<String?>(null) }
    var selectedProductQuantity by remember { mutableStateOf<Int?>(null) }
    var selectedBuyPrice by remember { mutableStateOf<Float?>(null) }
    var selectedSellPrice by remember { mutableStateOf<Float?>(null) }
    var selectedManufactureDate by remember { mutableStateOf<String?>(null) }
    var selectedExpiryDate by remember { mutableStateOf<String?>(null) }
    var selectedProductCategory by remember { mutableStateOf<String?>(null) }




    var showDialog by remember { mutableStateOf(false) }
    var showInventoryDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showInventoryHistDialog by remember {mutableStateOf(false)}


    // ✅ **Filter the list based on search query**
    val filteredProducts = inactiveProducts.filter {
        it.productName.contains(searchQuery.value, ignoreCase = true) ||
                it.productCode.contains(searchQuery.value, ignoreCase = true)
                || it.productCategory.contains(searchQuery.value, ignoreCase = true)

    }

    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedNotes by remember { mutableStateOf("") }


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
                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {


            // Fixed-position Back Button
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

            Spacer(modifier = Modifier.height(8.dp)) // space between icon and content


            // Title
            Text(
                text = "Manage Deleted Products",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id=R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "view inactive products here",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column( verticalArrangement = Arrangement.spacedBy(12.dp) ) {

                // Search Field
                TextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    placeholder = { Text(text = "Search...") },
                    leadingIcon = {

                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true
                )


                if (inactiveProducts.isEmpty()) {

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
                                text = "No Archived Products!",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                } else if (filteredProducts.isEmpty()) {

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
                                contentDescription = "No search Data",
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "No Products Found",
                                color = Color.Gray,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                } else {
//                    for (index in filteredProducts) {


                    for (index in filteredProducts.indices) {
                        val productItem = filteredProducts[index]

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                                .clickable {
                                    showSheet = true
                                    selectedProductId = productItem.productId

                                },
                            shape = RoundedCornerShape(20.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {

                                // 🔹 Top: Product Code + Name
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "product code: ${productItem.productCode}",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = colorResource(id = R.color.gray01)
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = productItem.productName,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            color = colorResource(id = R.color.dark)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // 🔹 Price Section
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = "Buy Price",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = productItem.buyPrice.toString(),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = colorResource(id = R.color.green)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "Sell Price",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = productItem.sellPrice.toString(),
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = colorResource(id = R.color.crimson)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // 🔹 Other details in a neat row format
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column {
                                        Text(
                                            text = "Category",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = productItem.productCategory,
                                            fontSize = 14.sp,
                                            color = colorResource(id = R.color.dark)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "Manufacture",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = productItem.manufactureDate ?: "not available",
                                            fontSize = 14.sp,
                                            color = colorResource(id = R.color.green)
                                        )
                                    }
                                    Column {
                                        Text(
                                            text = "Expiry",
                                            fontSize = 12.sp,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = productItem.expiryDate ?: "not available",
                                            fontSize = 14.sp,
                                            color = colorResource(id = R.color.crimson)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(12.dp))

                                // 🔹 Quantity at bottom center
                                Text(
                                    text = "Quantity: ${productItem.productQuantity}",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.prussian_blue),
                                    modifier = Modifier.align(Alignment.End)
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
                Text("Actions", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                )
//                Spacer(modifier = Modifier.height(4.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
//                Spacer(modifier = Modifier.height(4.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Delete Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                showDeleteDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.TrashAlt,
                            contentDescription = "Restore",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Delete product", fontSize = 16.sp)
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                productViewModel.restoreProductById(productId = selectedProductId!!)
                                Toast.makeText(
                                    context,
                                    "Product Restored",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.ArrowAltCircleLeft,
                            contentDescription = "Restore",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Restore product", fontSize = 16.sp)
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                                showInventoryHistDialog = true
                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.ClipboardList,
                            contentDescription = "clip",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Inventory History", fontSize = 16.sp)
                    }


                    if (showInventoryHistDialog && selectedProductId != null) {
                        StockHistPopUp(
                            onDismiss = { showInventoryHistDialog = false;
                                showSheet = false
                            },
                            productId = selectedProductId!!,
                        )
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


    // AlertDialog logic
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false;
                showSheet = false },
            title = { Text("Delete Product") },
            text = { Text("Do you want to delete this product permanently?") },
            confirmButton = {
                TextButton(onClick = {
                    productViewModel.hardDeleteProductById(productId = selectedProductId!!)
                    Toast.makeText(
                        context,
                        "Product Delete",
                        Toast.LENGTH_SHORT
                    ).show()
                    showDeleteDialog = false
                }) {
                    Text(
                        text = "Delete",
                        color = colorResource(id = R.color.red)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(
                        text = "Cancel",
                        color = colorResource(id = R.color.text_gray)
                    )
                }
            }
        )
    }

}












@Preview(showBackground = true)
@Composable
fun InactiveProductScreenPreview() {
    InactiveProductScreen(navController = rememberNavController())
}


