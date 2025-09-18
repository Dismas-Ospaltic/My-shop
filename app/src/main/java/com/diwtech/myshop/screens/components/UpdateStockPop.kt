package com.diwtech.myshop.screens.components



import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.diwtech.myshop.R
import com.diwtech.myshop.model.InventoryUpdateEntity
import com.diwtech.myshop.utils.dateFormated
import com.diwtech.myshop.viewmodel.InventoryUpdateViewModel
import com.diwtech.myshop.viewmodel.ProductViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStockPop(
    onDismiss: () -> Unit,
    productId: String,
    productCode: String,
    productQuantity: Int,
    buyPrice: Float
) {

    val backgroundColor = colorResource(id = R.color.prussian_blue)

    var productCode by remember { mutableStateOf(productCode) }
//    var productCategory by remember { mutableStateOf("") }
    var productQty by remember { mutableStateOf(productQuantity.toString()) }
    var updateQuantity by remember { mutableStateOf("") }
    var updateProductBuyPrice by remember { mutableStateOf("") }
    var productBuyPrice by remember { mutableStateOf(buyPrice.toString()) }
    val currentDate = remember { System.currentTimeMillis() }
    val todayDate = dateFormated(currentDate)
    var totalQty by remember { mutableFloatStateOf(0f) }




    val productViewModel: ProductViewModel = koinViewModel()
    val inventoryUpdateViewModel: InventoryUpdateViewModel = koinViewModel()




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
                Text(text = "Update Stock", fontWeight = FontWeight.Bold, fontSize = 18.sp)

                Column( verticalArrangement = Arrangement.spacedBy(12.dp) ){
                    OutlinedTextField(
                        value = updateQuantity ,
                        onValueChange = { updateQuantity  = it },
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


                    OutlinedTextField(
                        value = updateProductBuyPrice,
                        onValueChange = { input ->
                            // Remove commas automatically as user types
                            updateProductBuyPrice  = input.replace(",", "")
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


                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel", color = colorResource(id = R.color.prussian_blue))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        modifier = Modifier
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                        onClick = {


                            if(updateQuantity.isEmpty()){
                                Toast.makeText(context, "Please enter product quantity", Toast.LENGTH_LONG).show()
                                return@Button
                            }



                            if(updateProductBuyPrice.isEmpty()){
                                Toast.makeText(context, "Please enter product buy price", Toast.LENGTH_LONG).show()
                                return@Button
                            }




                            val cleanBuyPriceInput = updateProductBuyPrice.trim()
                            val amountBuyValue = cleanBuyPriceInput.toFloatOrNull()

                            if (amountBuyValue == null) {
                                Toast.makeText(context, "Please enter a valid number for product buy price", Toast.LENGTH_LONG).show()
                                return@Button
                            }


                            scope.launch {

                              inventoryUpdateViewModel.insertInventoryUpdate(InventoryUpdateEntity(
                                 date = todayDate,
                                  productId = productId,
                                  updateId = generateRandomNumber().toString(),
                                  productCode = productCode,
                                  productQuantity = updateQuantity.toInt(),
                                  previousProductQuantity = productQuantity,
                                  buyPrice = updateProductBuyPrice.toFloat(),
                                  previousBuyPrice = buyPrice
                              ))

                                productViewModel.updateProductQuantityById(productId = productId, newQuantity = (updateQuantity.toInt() + productQuantity))


                            }
                            onDismiss()

                        }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}

fun generateRandomNumber(): Int {
    return Random.nextInt(10000, 10000000)  // Generates a random number between 100000 (inclusive) and 1000000 (exclusive)
}