package com.diwtech.myshop.screens.components



import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.diwtech.myshop.R
import com.diwtech.myshop.model.GenSaleEntity
import com.diwtech.myshop.model.SingleProductSaleEntity
import com.diwtech.myshop.screens.SelectedProduct
import com.diwtech.myshop.utils.dateFormated
import com.diwtech.myshop.viewmodel.GenSaleViewModel
import com.diwtech.myshop.viewmodel.ProductViewModel
import com.diwtech.myshop.viewmodel.SingleProductSaleViewModel
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewSalePopup(
    onDismiss: () -> Unit,
    onClearProducts: () -> Unit, // new callback
    total: Double,
    selectedProducts: List<SelectedProduct>
) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    var expanded by remember { mutableStateOf(false) }
    var salesDescription by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("") }
    var amountPaid by remember { mutableStateOf("") }
    var amountRemain by remember { mutableStateOf("") }
    var totalSale by remember { mutableStateOf(0f) }
    var hasLowStock by remember { mutableStateOf(false) }

//    val singleSaleViewModel: SingleSaleViewModel = koinViewModel()
//    val singleProductSaleViewModel: SingleProductSaleViewModel = koinViewModel()
    val productViewModel: ProductViewModel = koinViewModel()
    val genSaleViewModel: GenSaleViewModel = koinViewModel()
    val singleProductSaleViewModel: SingleProductSaleViewModel = koinViewModel()

   val context = LocalContext.current

    hasLowStock = selectedProducts.any { item ->
        item.product.productQuantity < item.quantity.value
    }



    val currentDate = remember { System.currentTimeMillis() }
    val todayDate = dateFormated(currentDate)

    // Error states
    var paymentMethodError by remember { mutableStateOf(false) }
    var amountPaidError by remember { mutableStateOf(false) }

    val paymentMethodType = listOf("Cash", "Bank", "M-pesa", "Paypal")

    val idSales = generateTimestampBased10DigitNumberForReceipt()

    // Automatically calculate change when amountPaid changes
    LaunchedEffect(amountPaid) {
        val paid = amountPaid.toDoubleOrNull() ?: 0.0
        val change = paid - total
        amountRemain = change.toString()
//        totalSale =
//        amountRemain = if (change >= 0) change.toString() else "0"
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(text = "Complete Sales Now", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "Total Sales: $total", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

                // Payment Method Dropdown
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = paymentMethod,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Payment Method *") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryNotEditable),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        singleLine = true,
                        isError = paymentMethodError,
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
                        modifier = Modifier.background(Color.White)
                    ) {
                        paymentMethodType.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption, color = Color.Black) },
                                onClick = {
                                    paymentMethod = selectionOption
                                    paymentMethodError = false
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                if (paymentMethodError) {
                    Text(
                        text = "Please select a payment method",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                // Amount Paid
                OutlinedTextField(
                    value = amountPaid,
                    onValueChange = {
                        amountPaid = it
                        amountPaidError = false
                    },
                    label = { Text("Amount Paid *") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = amountPaidError,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true
                )
                if (amountPaidError) {
                    Text(
                        text = "Please enter a valid amount",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }

                // Change (Read-only)
                OutlinedTextField(
                    value = amountRemain,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Change") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = true
                )

                // Notes
                OutlinedTextField(
                    value = salesDescription,
                    onValueChange = { salesDescription = it },
                    label = { Text("Short Notes") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 200.dp)
                        .verticalScroll(rememberScrollState()),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
                        focusedContainerColor = Color.White.copy(alpha = 0.95f),
                        focusedBorderColor = backgroundColor,
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    ),
                    singleLine = false,
                    maxLines = 4
                )

                // Action buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) { Text("Cancel") }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        var valid = true
                        if (paymentMethod.isBlank()) {
                            paymentMethodError = true
                            valid = false
                        }
                        if (amountPaid.toDoubleOrNull() == null) {
                            amountPaidError = true
                            valid = false
                        }
                        if (valid) {
                          if(hasLowStock){
                              Toast.makeText(context, "some product you selected has Low Stock! sales cannot be completed", Toast.LENGTH_SHORT).show()
                          }else{

                            // Proceed with saving
                            genSaleViewModel.insertGenSale(
                                GenSaleEntity(
                                 date = todayDate,
                                    receipt = idSales.toString(),
                                    saleType = paymentMethod,
                                    description = salesDescription,
                                    totalSale = total.toFloat(),
                                    totalPaid = amountPaid.toFloat(),
                                    change = amountRemain.toFloat()
                                )
                            )

                            selectedProducts.forEachIndexed { index, item ->


                                singleProductSaleViewModel.insertSingleProducts(
                                    SingleProductSaleEntity(
                                        date = todayDate,
                                        receipt = idSales.toString(),
                                        productName = item.product.productName,
                                        productId = item.product.productId,
                                        productCode = item.product.productCode,
                                        price = item.product.sellPrice,
                                        quantity = item.quantity.value,
                                        total = (item.product.sellPrice * item.quantity.value),
                                    )
                                )


                                //reduce the stock when sale is added
                                val newProductStock = item.product.productQuantity - item.quantity.value

                                productViewModel.updateProductQuantityById(productId = item.product.productId, newProductStock)
                                }
                              onClearProducts() // ✅ clear products
                              onDismiss()       // ✅ close dialog
                            }


                        }
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}


fun generateTimestampBased10DigitNumberForReceipt(): Long {
    val timestampPart = (System.currentTimeMillis() / 1000) % 100000 // Last 5 digits of timestamp (seconds)
    val randomPart = (10000..99999).random() // 5 random digits
    return "$timestampPart$randomPart".toLong()
}


