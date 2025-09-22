package com.diwtech.myshop.screens




import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.diwtech.myshop.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.diwtech.myshop.BannerAd
import com.diwtech.myshop.model.ProductEntity
import com.diwtech.myshop.screens.components.DatePickerField
import com.diwtech.myshop.utils.DynamicStatusBar
import com.diwtech.myshop.utils.dateFormated
import com.diwtech.myshop.viewmodel.ProductViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Plus
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    DynamicStatusBar(colorResource(id = R.color.white))

    val context = LocalContext.current

    var productCode by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }
//    var productCategory by remember { mutableStateOf("") }
    var productQty by remember { mutableStateOf("") }
    var productBuyPrice by remember { mutableStateOf("") }
    var productSellPrice by remember { mutableStateOf("") }
    var productManufactureDate by remember { mutableStateOf("") }
    var productExpiry by remember { mutableStateOf("") }
    val currentDate = remember { System.currentTimeMillis() }
    val todayDate = dateFormated(currentDate)




    val productViewModel: ProductViewModel = koinViewModel()





    var productCategory by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }


    val categoryType = listOf(
        "other", "soap", "kitchen ware", "electronics"
    )

    Scaffold(
        bottomBar = {
            Column{
                // ✅ Show banner ad
                BannerAd(
                    modifier = Modifier
                        .fillMaxWidth()
//                            .padding(horizontal = 16.dp)
//                        .padding(4.dp) // optional
                )
            // Bottom bar container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars) // ✅ Push above system nav bar
            ) {
                Button(
                    onClick = { /* TODO: Handle click */

                        if (productCode.isEmpty()) {
                            Toast.makeText(context, "Please enter product code", Toast.LENGTH_LONG)
                                .show()
                            return@Button
                        }

                        if (productName.isEmpty()) {
                            Toast.makeText(context, "Please enter product name", Toast.LENGTH_LONG)
                                .show()
                            return@Button
                        }


                        if (productQty.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Please enter product quantity",
                                Toast.LENGTH_LONG
                            ).show()
                            return@Button
                        }



                        if (productBuyPrice.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Please enter product buy price",
                                Toast.LENGTH_LONG
                            ).show()
                            return@Button
                        }

                        if (productSellPrice.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Please enter product sell price",
                                Toast.LENGTH_LONG
                            ).show()
                            return@Button
                        }


                        val cleanBuyPriceInput = productBuyPrice.trim()
                        val amountBuyValue = cleanBuyPriceInput.toFloatOrNull()

                        if (amountBuyValue == null) {
                            Toast.makeText(
                                context,
                                "Please enter a valid number for product buy price",
                                Toast.LENGTH_LONG
                            ).show()
                            return@Button
                        }


                        val cleanSellPriceInput = productSellPrice.trim()
                        val amountSellValue = cleanSellPriceInput.toFloatOrNull()

                        if (amountSellValue == null) {
                            Toast.makeText(
                                context,
                                "Please enter a valid number for product sell price",
                                Toast.LENGTH_LONG
                            ).show()
                            return@Button
                        }


                        productViewModel.insertProduct(
                            ProductEntity(
                                productId = generateSixDigitRandomNumber().toString(),
                                productCode = productCode,
                                productName = productName,
                                productCategory = productCategory,
                                productQuantity = productQty.toInt(),
                                buyPrice = productBuyPrice.toFloat(),
                                sellPrice = productSellPrice.toFloat(),
                                manufactureDate = productManufactureDate,
                                expiryDate = productExpiry,
                                date = todayDate
                            )
                        )

                        navController.popBackStack()


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
                        text = "Save Product",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            } }
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
                text = "Add Product",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id=R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "Enter the Details of the products in your shop",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column( verticalArrangement = Arrangement.spacedBy(12.dp) ){
                OutlinedTextField(
                    value = productCode ,
                    onValueChange = { productCode  = it },
                    label = { Text("Product Code*") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true,
                )


                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    label = { Text("product Name*") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true,
                )

                OutlinedTextField(
                    value = productQty,
                    onValueChange = { productQty = it },
                    label = { Text("Product Quantity*") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )


                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = productCategory,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Category") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable),
//                        .menuAnchor(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                            focusedContainerColor = Color.White.copy(alpha = 0.95f),
                            focusedBorderColor = backgroundColor,
                            unfocusedBorderColor = Color.Gray,
                            focusedLabelColor = backgroundColor,
                            cursorColor = backgroundColor
                        )
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .background(Color.White) // ✅ White background for the dropdown menu
                    ) {
                        categoryType.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption, color = Color.Black) }, // ✅ Black text
                                onClick = {
                                    productCategory = selectionOption
                                    expanded = false
                                }
                            )
                        }
                    }
                }


                OutlinedTextField(
                    value = productBuyPrice,
                    onValueChange = { input ->
                        // Remove commas automatically as user types
                        productBuyPrice  = input.replace(",", "")
                    },
//                    onValueChange = { productBuyPrice  = it },
                    label = { Text("Product buy Price*") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )


                OutlinedTextField(
                    value = productSellPrice,
                    onValueChange = { input ->
                        // Remove commas automatically as user types
                        productSellPrice  = input.replace(",", "")
                    },
                    label = { Text("Product Sell Price*") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )



                DatePickerField(
                    label = "Manufacture Date",
                    value = productManufactureDate, // <-- This is from your parent state
                    onDateSelected = { date -> productManufactureDate = date }
                )



                DatePickerField(
                    label = "Expiry Date",
                    value = productExpiry, // <-- This is from your parent state
                    onDateSelected = { date -> productExpiry = date }
                )


            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun AddProductScreenPreview() {
    AddProductScreen(navController = rememberNavController())
}


fun generateSixDigitRandomNumber(): Int {
    return Random.nextInt(1000000, 1000000000)  // Generates a random number between 100000 (inclusive) and 1000000 (exclusive)
}



