//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AddSalesScreen(navController: NavController) {
//    val backgroundColor = colorResource(id = R.color.prussian_blue)
//    val searchQuery = remember { mutableStateOf("") }
//
//    // Use a mutable list of selected products with quantity
//    val selectedProducts = remember { mutableStateListOf<SelectedProduct>() }
//
//    // Product list
//    val productList = listOf(
//        Product("P001", "Smartphone", "Electronics", 599.99),
//        Product("P002", "Laptop", "Electronics", 999.99),
//        Product("P003", "Office Chair", "Furniture", 149.99),
//        Product("P004", "Water Bottle", "Accessories", 19.99),
//        Product("P005", "Wireless Mouse", "Electronics", 29.99)
//    )
//
//    // Filter products based on search
//    val filteredProducts = productList.filter {
//        it.productName.contains(searchQuery.value, ignoreCase = true) ||
//                it.productCode.contains(searchQuery.value, ignoreCase = true)
//    }
//
//    // Calculate total price dynamically
//    val totalPrice = selectedProducts.sumOf { it.product.price * it.quantity }
//
//    Scaffold(
//        bottomBar = {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//                    .windowInsetsPadding(WindowInsets.navigationBars)
//            ) {
//                Button(
//                    onClick = { /* Handle checkout */ },
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
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .verticalScroll(rememberScrollState())
//        ) {
//            // Back Button
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
//            Spacer(modifier = Modifier.height(8.dp))
//
//            // Title
//            Text(
//                text = "Add Sales",
//                modifier = Modifier.padding(horizontal = 16.dp),
//                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
//                color = colorResource(id = R.color.dark)
//            )
//
//            Text(
//                text = "Sale products",
//                modifier = Modifier.padding(horizontal = 16.dp),
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Gray
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
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
//                // Search result card overlay
//                if (searchQuery.value.isNotEmpty() && filteredProducts.isNotEmpty()) {
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 60.dp)
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
//                                            if (selectedProducts.none { it.product.productCode == product.productCode }) {
//                                                selectedProducts.add(SelectedProduct(product, 1))
//                                            }
//                                            searchQuery.value = ""
//                                        }
//                                        .padding(12.dp)
//                                ) {
//                                    Text(product.productName)
//                                    Spacer(modifier = Modifier.weight(1f))
//                                    Text("Ksh ${product.price}")
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//// Data class for product
//data class Product(
//    val productCode: String,
//    val productName: String,
//    val category: String,
//    val price: Double
//)
//
//// Data class for selected product with quantity
//data class SelectedProduct(
//    val product: Product,
//    var quantity: Int
//)
