package com.ditech.myshop.screens.components


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.core.content.ContextCompat
import com.ditech.myshop.R
import com.ditech.myshop.utils.dateFormated
import com.ditech.myshop.viewmodel.ProductViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductPopUp(
//    initialText: String,
    onDismiss: () -> Unit,
    productId: String,
    productCode: String,
    productName: String,
    productQuantity: Int,
    buyPrice: Float,
    sellPrice: Float,
    manufactureDate: String?,
    expiryDate: String?,
    productCategory: String

) {

    val backgroundColor = colorResource(id = R.color.prussian_blue)

//    var expanded by remember { mutableStateOf(false) }
//    var expanded01 by remember { mutableStateOf(false) }
//
//    var title by remember { mutableStateOf(eventTitle) }
//    var venue by remember { mutableStateOf(eventVenue) }
//    var eventDescription by remember { mutableStateOf(noteDescription) }
//    var priority by remember { mutableStateOf(eventPriority) }
//    var category by remember { mutableStateOf(eventCategory) }
//    var selectedDate by remember { mutableStateOf(eventDate) }
//    var startTime by remember { mutableStateOf(eventStartTime) }
//    var endTime by remember { mutableStateOf(eventEndTime) }

//////////////////////
    var productCode by remember { mutableStateOf(productCode) }
    var productName by remember { mutableStateOf(productName) }
//    var productCategory by remember { mutableStateOf("") }
    var productQty by remember { mutableStateOf(productQuantity.toString()) }
    var productBuyPrice by remember { mutableStateOf(buyPrice.toString()) }
    var productSellPrice by remember { mutableStateOf(sellPrice.toString()) }
    var productManufactureDate by remember { mutableStateOf(manufactureDate.toString()) }
    var productExpiry by remember { mutableStateOf(expiryDate.toString()) }
    val currentDate = remember { System.currentTimeMillis() }
    val todayDate = dateFormated(currentDate)




    val productViewModel: ProductViewModel = koinViewModel()

    var productCategory by remember { mutableStateOf(productCategory) }
    var expanded by remember { mutableStateOf(false) }


    val categoryType = listOf(
        "other", "soap", "kitchen ware", "electronics"
    )

    //////////////////





    val context = LocalContext.current



    // ✅ 1. Get a coroutine scope tied to the composable's lifecycle
    val scope = rememberCoroutineScope()


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
                    .verticalScroll(rememberScrollState()), // Adjust for keyboard
                verticalArrangement = Arrangement.spacedBy(12.dp)

            ) {
                Text(text = "Edit", fontWeight = FontWeight.Bold, fontSize = 18.sp)


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





                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                        onClick = {


                        if(productCode.isEmpty()){
                            Toast.makeText(context, "Please enter product code", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        if(productName.isEmpty()){
                            Toast.makeText(context, "Please enter product name", Toast.LENGTH_LONG).show()
                            return@Button
                        }


                        if(productQty.isEmpty()){
                            Toast.makeText(context, "Please enter product quantity", Toast.LENGTH_LONG).show()
                            return@Button
                        }



                        if(productBuyPrice.isEmpty()){
                            Toast.makeText(context, "Please enter product buy price", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        if(productSellPrice.isEmpty()){
                            Toast.makeText(context, "Please enter product sell price", Toast.LENGTH_LONG).show()
                            return@Button
                        }


                        val cleanBuyPriceInput = productBuyPrice.trim()
                        val amountBuyValue = cleanBuyPriceInput.toFloatOrNull()

                        if (amountBuyValue == null) {
                            Toast.makeText(context, "Please enter a valid number for product buy price", Toast.LENGTH_LONG).show()
                            return@Button
                        }



                        val cleanSellPriceInput = productSellPrice.trim()
                        val amountSellValue = cleanSellPriceInput.toFloatOrNull()

                        if (amountSellValue == null) {
                            Toast.makeText(context, "Please enter a valid number for product sell price", Toast.LENGTH_LONG).show()
                            return@Button
                        }

                        scope.launch {


                        }

                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}