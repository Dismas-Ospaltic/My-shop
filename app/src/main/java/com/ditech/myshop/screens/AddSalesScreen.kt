package com.ditech.myshop.screens


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ditech.myshop.model.ProductEntity
import com.ditech.myshop.screens.components.AddNewSalePopup
import com.ditech.myshop.screens.components.EditProductPopUp
import com.ditech.myshop.utils.DynamicStatusBar
import com.ditech.myshop.viewmodel.ProductViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Minus
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.Search
import compose.icons.fontawesomeicons.solid.Times
import org.koin.androidx.compose.koinViewModel
import kotlin.collections.remove


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddSalesScreen(navController: NavController) {
//    val backgroundColor = colorResource(id = R.color.prussian_blue)
//    val searchQuery = remember { mutableStateOf("") }
//    val selectedProducts = remember { mutableStateListOf<Product>() }
//    DynamicStatusBar(colorResource(id = R.color.white))
//
//// Create a list of products
//    val productList = listOf(
//        Product("P001", "Smartphone", "Electronics", 599.99),
//        Product("P002", "Laptop", "Electronics", 999.99),
//        Product("P003", "Office Chair", "Furniture", 149.99),
//        Product("P004", "Water Bottle", "Accessories", 19.99),
//        Product("P005", "Wireless Mouse", "Electronics", 29.99)
//    )
//
//
//    // Filter products based on search
//    val filteredProducts = productList.filter {
//        it.productName.contains(searchQuery.value, ignoreCase = true) ||
//                it.productCode.contains(searchQuery.value, ignoreCase = true)
//    }
//
//    val context = LocalContext.current
//
//// Calculate total price dynamically
//    val totalPrice = selectedProducts.sumOf { it.price }
//
//    // Calculate total price dynamically
//    val totalPrice = selectedProducts.sumOf { it.product.price * it.quantity }
//
//    Scaffold(
//        bottomBar = {
//            // Bottom bar container
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//                    .windowInsetsPadding(WindowInsets.navigationBars) // ✅ Push above system nav bar
//            ) {
//                Button(
//                    onClick = { /* TODO: Handle click */ },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(56.dp),
//                    shape = RoundedCornerShape(12.dp),
//                    colors = ButtonDefaults.buttonColors(containerColor = backgroundColor)
//                ) {
//                    Icon(
//                        imageVector = FontAwesomeIcons.Solid.Plus,
//                        contentDescription = null,
//                        tint = Color.White,
//                        modifier = Modifier.size(20.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = "Save Sales",
//                        color = Color.White,
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//                }
//            }
//        }
//    ) { paddingValues ->
//        // Scrollable content
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
//                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
//                    top = paddingValues.calculateTopPadding(),
//                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
//                    bottom = paddingValues.calculateBottomPadding()
//                )
//                .verticalScroll(rememberScrollState())
//        ) {
//
//
//            // Fixed-position Back Button
//            IconButton(
//                onClick = { navController.popBackStack() },
//                modifier = Modifier
//                    .padding(16.dp)
//                    .size(40.dp)
//                    .background(
//                        color = colorResource(id = R.color.prussian_blue),
//                        shape = CircleShape
//                    )
//                    .align(Alignment.Start)
//            ) {
//                Icon(
//                    imageVector = FontAwesomeIcons.Solid.ArrowLeft,
//                    contentDescription = "Back",
//                    tint = Color.White,
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.height(8.dp)) // space between icon and content
//
//
//            // Title
//            Text(
//                text = "Add Sales",
//                modifier = Modifier
//                    .padding(end = 16.dp, start = 16.dp),
//                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
//                color = colorResource(id=R.color.dark)
//            )
////                    Spacer(modifier = Modifier.height(8.dp))
//            // Subtitle
//            Text(
//                text = "sale products",
//                modifier = Modifier
//                    .padding(end = 16.dp, start = 16.dp),
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Gray
//            )
//            Spacer(modifier = Modifier.height(16.dp))
////
////            Column( verticalArrangement = Arrangement.spacedBy(12.dp) ){
////
////                // Search Field
////                TextField(
////                    value = searchQuery.value,
////                    onValueChange = { searchQuery.value = it },
////                    placeholder = { Text(text = "Search...") },
////                    leadingIcon = {
////                        Icon(
////                            imageVector = FontAwesomeIcons.Solid.Search,
////                            contentDescription = "Search Icon",
////                            tint = Color.Gray,
////                            modifier = Modifier.size(24.dp)
////                        )
////                    },
////                    modifier = Modifier
////                        .weight(1f)
////                        .clip(RoundedCornerShape(12.dp))
////                        .background(colorResource(id=R.color.light_bg_color)),
////                    colors = TextFieldDefaults.colors(
////                        focusedContainerColor = Color.Transparent,
////                        unfocusedContainerColor = Color.Transparent,
////                        disabledContainerColor = Color.Transparent,
////                        cursorColor = Color.Black,
////                        focusedIndicatorColor = Color.Transparent,
////                        unfocusedIndicatorColor = Color.Transparent
////                    ),
////                    singleLine = true
////                )
////
////            }
//            Box(modifier = Modifier.fillMaxWidth()) {
//                Column {
//                    // Search Field
//                    TextField(
//                        value = searchQuery.value,
//                        onValueChange = { searchQuery.value = it },
//                        placeholder = { Text(text = "Search...") },
//                        leadingIcon = {
//                            Icon(
//                                imageVector = FontAwesomeIcons.Solid.Search,
//                                contentDescription = "Search Icon",
//                                tint = Color.Gray,
//                                modifier = Modifier.size(24.dp)
//                            )
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clip(RoundedCornerShape(12.dp))
//                            .background(colorResource(id = R.color.light_bg_color)),
//                        colors = TextFieldDefaults.colors(
//                            focusedContainerColor = Color.Transparent,
//                            unfocusedContainerColor = Color.Transparent,
//                            disabledContainerColor = Color.Transparent,
//                            cursorColor = Color.Black,
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        ),
//                        singleLine = true
//                    )
//
////                    // Selected products for checkout
////                    if (selectedProducts.isNotEmpty()) {
////                        Text("Products", fontWeight = FontWeight.Bold)
////                        Spacer(modifier = Modifier.height(12.dp))
////                        selectedProducts.forEach { product ->
////                            Card(
////                                modifier = Modifier
////                                    .fillMaxWidth()
////                                    .padding(vertical = 4.dp),
////                                colors = CardDefaults.cardColors(containerColor = Color.White),
////                                shape = RoundedCornerShape(12.dp),
////                                elevation = CardDefaults.cardElevation(4.dp)
////                            ) {
////                                Row(
////                                    modifier = Modifier
////                                        .fillMaxWidth()
////                                        .padding(12.dp),
////                                    horizontalArrangement = Arrangement.SpaceBetween,
////                                    verticalAlignment = Alignment.CenterVertically
////                                ) {
////                                    Column {
////                                        Text(product.productName, fontWeight = FontWeight.Bold)
////                                        Text(product.productCode, color = Color.Gray)
////                                    }
////                                    Row(verticalAlignment = Alignment.CenterVertically) {
////                                        Text("Ksh ${product.price}")
////                                        Spacer(modifier = Modifier.width(8.dp))
////                                        IconButton(onClick = { selectedProducts.remove(product) }) {
////                                            Icon(
////                                                imageVector = FontAwesomeIcons.Solid.Times,
////                                                contentDescription = "Remove",
////                                                tint = Color.Red,
////                                                modifier = Modifier.size(20.dp)
////                                            )
////                                        }
////                                    }
////                                }
////                            }
////                        }
////
////
////                        // Total Section Card
////                        Spacer(modifier = Modifier.height(8.dp))
////                        Card(
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .padding(vertical = 8.dp),
////                            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.light_bg_color)),
////                            shape = RoundedCornerShape(12.dp),
////                            elevation = CardDefaults.cardElevation(2.dp)
////                        ) {
////                            Row(
////                                modifier = Modifier
////                                    .fillMaxWidth()
////                                    .padding(16.dp),
////                                horizontalArrangement = Arrangement.SpaceBetween,
////                                verticalAlignment = Alignment.CenterVertically
////                            ) {
////                                Text("Total", fontWeight = FontWeight.Bold)
////                                Text("Ksh %.2f".format(totalPrice), fontWeight = FontWeight.Bold)
////                            }
////                        }
////                    }
//
//
//                    // Selected products list
//                    if (selectedProducts.isNotEmpty()) {
//                        Spacer(modifier = Modifier.height(12.dp))
//                        selectedProducts.forEach { selected ->
//                            Card(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(vertical = 4.dp),
//                                colors = CardDefaults.cardColors(containerColor = Color.White),
//                                shape = RoundedCornerShape(12.dp),
//                                elevation = CardDefaults.cardElevation(4.dp)
//                            ) {
//                                Column(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(12.dp)
//                                ) {
//                                    Row(
//                                        horizontalArrangement = Arrangement.SpaceBetween,
//                                        verticalAlignment = Alignment.CenterVertically,
//                                        modifier = Modifier.fillMaxWidth()
//                                    ) {
//                                        Column {
//                                            Text(selected.product.productName, fontWeight = FontWeight.Bold)
//                                            Text(selected.product.productCode, color = Color.Gray)
//                                        }
//                                        IconButton(onClick = { selectedProducts.remove(selected) }) {
//                                            Icon(
//                                                imageVector = FontAwesomeIcons.Solid.Times,
//                                                contentDescription = "Remove",
//                                                tint = Color.Red,
//                                                modifier = Modifier.size(20.dp)
//                                            )
//                                        }
//                                    }
//
//                                    Spacer(modifier = Modifier.height(6.dp))
//
//                                    // Quantity selector & Subtotal
//                                    Row(
//                                        horizontalArrangement = Arrangement.SpaceBetween,
//                                        verticalAlignment = Alignment.CenterVertically,
//                                        modifier = Modifier.fillMaxWidth()
//                                    ) {
//                                        Row(verticalAlignment = Alignment.CenterVertically) {
//                                            IconButton(onClick = {
//                                                if (selected.quantity > 1) {
//                                                    selected.quantity--
//                                                }
//                                            }) {
//                                                Icon(
//                                                    imageVector = FontAwesomeIcons.Solid.Minus,
//                                                    contentDescription = "Decrease",
//                                                    tint = Color.Black
//                                                )
//                                            }
//                                            Text(
//                                                text = selected.quantity.toString(),
//                                                modifier = Modifier.padding(horizontal = 8.dp),
//                                                fontWeight = FontWeight.Bold
//                                            )
//                                            IconButton(onClick = { selected.quantity++ }) {
//                                                Icon(
//                                                    imageVector = FontAwesomeIcons.Solid.Plus,
//                                                    contentDescription = "Increase",
//                                                    tint = Color.Black
//                                                )
//                                            }
//                                        }
//                                        Text(
//                                            "Subtotal: Ksh %.2f".format(selected.product.price * selected.quantity),
//                                            fontWeight = FontWeight.Bold
//                                        )
//                                    }
//                                }
//                            }
//                        }
//
//                        // Total Section Card
//                        Spacer(modifier = Modifier.height(8.dp))
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 8.dp),
//                            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.light_bg_color)),
//                            shape = RoundedCornerShape(12.dp),
//                            elevation = CardDefaults.cardElevation(2.dp)
//                        ) {
//                            Row(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp),
//                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Text("Total", fontWeight = FontWeight.Bold)
//                                Text("Ksh %.2f".format(totalPrice), fontWeight = FontWeight.Bold)
//                            }
//                        }
//                    }
//                }
//
//
//
//
//
//                // Search result card overlay (high Z-index)
//                if (searchQuery.value.isNotEmpty() && filteredProducts.isNotEmpty()) {
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 60.dp) // just below search field
//                            .align(Alignment.TopCenter),
//                        colors = CardDefaults.cardColors(containerColor = Color.White),
//                        shape = RoundedCornerShape(12.dp),
//                        elevation = CardDefaults.cardElevation(8.dp)
//                    ) {
//                        Column {
//                            filteredProducts.forEach { product ->
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .clickable {
//                                            if (!selectedProducts.contains(product)) {
//                                                selectedProducts.add(product)
//                                            }
//                                            searchQuery.value = ""
//                                        }
//                                        .padding(12.dp)
//                                ) {
//                                    Text(product.productCode)
//                                    Text(product.productName)
//                                    Spacer(modifier = Modifier.weight(1f))
//                                    Text("Ksh ${product.price}")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//    }
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun AddSalesScreenPreview() {
//    AddSalesScreen(navController = rememberNavController())
//}
//
//
//
//
//// Define the data class
//data class Product(
//    val productCode: String,
//    val productName: String,
//    val category: String,
//    val price: Double
//)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSalesScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    val searchQuery = remember { mutableStateOf("") }



    val productViewModel: ProductViewModel = koinViewModel()
    val activeProducts by productViewModel.activeProducts.collectAsState()
    var showSalesDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Use a mutable list of selected products with quantity
//    val selectedProducts = remember { mutableStateListOf<SelectedProduct>() }

    val selectedProducts = remember { mutableStateListOf<SelectedProduct>() }



//    // Product list
//    val productList = listOf(
//        Product("P001", "Smartphone", "Electronics", 599.99),
//        Product("P002", "Laptop", "Electronics", 999.99),
//        Product("P003", "Office Chair", "Furniture", 149.99),
//        Product("P004", "Water Bottle", "Accessories", 19.99),
//        Product("P005", "Wireless Mouse", "Electronics", 29.99)
//    )





//    // Filter products based on search
//    val filteredProducts = productList.filter {
//        it.productName.contains(searchQuery.value, ignoreCase = true) ||
//                it.productCode.contains(searchQuery.value, ignoreCase = true)
//    }

    // ✅ **Filter the list based on search query**
    val filteredProducts = activeProducts.filter {
        it.productName.contains(searchQuery.value, ignoreCase = true) ||
                it.productCode.contains(searchQuery.value, ignoreCase = true)
                || it.productCategory.contains(searchQuery.value, ignoreCase = true)

    }

    // Calculate total price dynamically
//    val totalPrice = selectedProducts.sumOf { it.product.price * it.quantity }
//    val totalPrice = selectedProducts.sumOf { it.product.price * it.quantity.value }
//    val totalPrice = selectedProducts.sumOf { it.product.sellPrice * it.quantity.value }
    val totalPrice = selectedProducts.sumOf { (it.product.sellPrice * it.quantity.value).toDouble() }



    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                Button(
                    onClick = {
                        // Selected products list
                        if (selectedProducts.isNotEmpty()) {
                          showSalesDialog = true
                        }else{
                            Toast.makeText(context, "No products selected", Toast.LENGTH_SHORT).show()
                        }

                    /* Handle checkout */ },
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
                        text = "Save Sales",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
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
                text = "Add Sales",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.dark)
            )

            Text(
                text = "Sale products",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Column {
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
                            .clip(RoundedCornerShape(12.dp))
                            .background(colorResource(id = R.color.light_bg_color)),
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

                    // Selected products list
                    if (selectedProducts.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        selectedProducts.forEach { selected ->



                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column {
                                            Text(selected.product.productName, fontWeight = FontWeight.Bold)
                                            Text(selected.product.productCode, color = Color.Gray)
                                        }
                                        IconButton(onClick = { selectedProducts.remove(selected) }) {
                                            Icon(
                                                imageVector = FontAwesomeIcons.Solid.Times,
                                                contentDescription = "Remove",
                                                tint = Color.Red,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(6.dp))

                                    // Quantity selector & Subtotal
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {

                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            IconButton(onClick = {
                                                if (selected.quantity.value > 1) {
                                                    selected.quantity.value--
                                                }
                                            }) {
                                                Icon(FontAwesomeIcons.Solid.Minus, contentDescription = "Decrease")
                                            }

                                            Text(
                                                text = selected.quantity.value.toString(),
                                                style = MaterialTheme.typography.bodyMedium
                                            )

                                            IconButton(onClick = {
                                                selected.quantity.value++
                                            }) {
                                                Icon(FontAwesomeIcons.Solid.Plus, contentDescription = "Increase")
                                            }
                                        }

//                                        Row(verticalAlignment = Alignment.CenterVertically) {
//                                            IconButton(onClick = {
//                                                if (selected.quantity > 1) {
//                                                    selected.quantity--
//                                                }
//                                            }) {
//                                                Icon(
//                                                    imageVector = FontAwesomeIcons.Solid.Minus,
//                                                    contentDescription = "Decrease",
//                                                    tint = Color.Black
//                                                )
//                                            }
//                                            Text(
//                                                text = selected.quantity.toString(),
//                                                modifier = Modifier.padding(horizontal = 8.dp),
//                                                fontWeight = FontWeight.Bold
//                                            )
//                                            IconButton(onClick = { selected.quantity++ }) {
//                                                Icon(
//                                                    imageVector = FontAwesomeIcons.Solid.Plus,
//                                                    contentDescription = "Increase",
//                                                    tint = Color.Black
//                                                )
//                                            }
//                                        }
//                                        Text(
//                                            "Subtotal: Ksh %.2f".format(selected.product.price * selected.quantity),
//                                            fontWeight = FontWeight.Bold
//                                        )
                                        Text(
//                                            text = "Subtotal: Ksh %.2f".format(selected.product.price * selected.quantity.value),
                                            text = "Subtotal: %.2f".format(selected.product.sellPrice * selected.quantity.value),
                                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                        )

                                    }
                                }
                            }
                        }

                        // Total Section Card
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.light_bg_color)),
                            shape = RoundedCornerShape(12.dp),
                            elevation = CardDefaults.cardElevation(2.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Total", fontWeight = FontWeight.Bold)
                                Text("%.2f".format(totalPrice), fontWeight = FontWeight.Bold)
                            }
                        }
                    }else{


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
                                    contentDescription = "No search Data",
                                    modifier = Modifier.size(120.dp)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "Search Products by name, code or Category to add them for checkout!",
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }

                // Search result card overlay
                if (searchQuery.value.isNotEmpty() && filteredProducts.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 60.dp)
                            .align(Alignment.TopCenter),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        Column {
                            filteredProducts.forEach { product ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            if (!selectedProducts.any { it.product.productCode == product.productCode }) {
                                                selectedProducts.add(SelectedProduct(product, mutableStateOf(1)))
                                            }
                                            searchQuery.value = ""
                                        }
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column {
                                        Text(
                                            text = "code: " + product.productCode,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.Gray
                                        )
                                        Text(
                                            text = product.productName,
                                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                        )
                                        Text(
                                            text = "%.2f".format(product.sellPrice),
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.Gray
                                        )

                                        Text(
                                            text = "category: " + product.productCategory,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.Gray
                                        )
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }


    if (showSalesDialog) {
       AddNewSalePopup(
            onDismiss = { showSalesDialog = false},
            total = totalPrice,
            selectedProducts = selectedProducts
        )
    }

}

// Data class for product
//data class Product(
//    val productCode: String,
//    val productName: String,
//    val category: String,
//    val price: Double
//)

// Data class for selected product with quantity
data class SelectedProduct(
    val product: ProductEntity,
//    var quantity: Int
    val quantity: MutableState<Int> = mutableStateOf(1)
)





