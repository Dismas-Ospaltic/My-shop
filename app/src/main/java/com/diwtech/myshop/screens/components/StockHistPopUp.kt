package com.diwtech.myshop.screens.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.diwtech.myshop.R
import com.diwtech.myshop.utils.dateFormated
import com.diwtech.myshop.viewmodel.InventoryUpdateViewModel
import com.diwtech.myshop.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockHistPopUp(
    onDismiss: () -> Unit,
    productId: String,
) {

    val backgroundColor = colorResource(id = R.color.prussian_blue)

    val currentDate = remember { System.currentTimeMillis() }
    val todayDate = dateFormated(currentDate)





    val productViewModel: ProductViewModel = koinViewModel()
    val inventoryUpdateViewModel: InventoryUpdateViewModel = koinViewModel()
    val allInventoryUpdatesById by inventoryUpdateViewModel.allInventoryUpdatesByProductId.collectAsState()


    LaunchedEffect(productId) {
        inventoryUpdateViewModel.getInventoryUpdatesByProductId(productId)
    }


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
                Text(text = "Stock Update History", fontWeight = FontWeight.Bold, fontSize = 18.sp)

//                Column( verticalArrangement = Arrangement.spacedBy(12.dp) ){
//                }



//                for (index in filteredWatchList.indices) {
//                    val item = filteredWatchList[index]


//                repeat(5) { index ->

                    for (inventoryUpdate in allInventoryUpdatesById) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF9F9F9)) // soft background
                            .clickable { }
                            .padding(16.dp)
                    ) {
                        // Date
                        Text(
                            text = inventoryUpdate.date,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        )

                        Spacer(Modifier.height(8.dp))

                        // Quantities
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Quantity: ${inventoryUpdate.productQuantity}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                "Previous Quantity: ${inventoryUpdate.previousProductQuantity}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }

                        Spacer(Modifier.height(6.dp))

                        // Prices
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Buy price: ${inventoryUpdate.buyPrice}",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                            Text(
                                "Previous buy price: ${inventoryUpdate.previousBuyPrice}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }

                    // Divider between items (except last one)
                    if (inventoryUpdate != allInventoryUpdatesById.last()) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            thickness = 1.dp,
                            color = Color(0xFFDDDDDD) // subtle divider color
                        )
                    }

//                    // Divider except after last item
//                    if (index < books.lastIndex) {
//                        HorizontalDivider(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 8.dp),
//                            thickness = 1.dp,
//                            color = Color(0xFFE0E0E0)
//                        )
//                    }
                }



                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = Modifier
                            .height(56.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                        onClick = {
                            onDismiss()

                        }) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

