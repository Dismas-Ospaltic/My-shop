package com.ditech.myshop.screens.components


import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.ditech.myshop.R
import com.ditech.myshop.model.InventoryUpdateEntity
import com.ditech.myshop.utils.dateFormated
import com.ditech.myshop.viewmodel.InventoryUpdateViewModel
import com.ditech.myshop.viewmodel.ProductViewModel
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.InfoCircle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import kotlin.compareTo
import kotlin.random.Random

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

