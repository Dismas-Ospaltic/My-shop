//package com.st11.salesnote.screens
//
//
//
//
//import android.content.Intent
//import android.widget.Toast
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.combinedClickable
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material3.*
//import androidx.compose.material3.HorizontalDivider
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.hapticfeedback.HapticFeedbackType
//import androidx.compose.ui.input.pointer.pointerInput
//import com.st11.salesnote.R
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalHapticFeedback
//import androidx.compose.ui.platform.LocalLayoutDirection
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import com.st11.salesnote.navigation.Screen
//import com.st11.salesnote.screens.components.AddSalePopUp
//import com.st11.salesnote.utils.DynamicStatusBar
//import com.st11.salesnote.utils.formatDate
//
//import compose.icons.FontAwesomeIcons
//import compose.icons.fontawesomeicons.Regular
//import compose.icons.fontawesomeicons.Solid
//import compose.icons.fontawesomeicons.regular.ThumbsUp
//import compose.icons.fontawesomeicons.regular.TrashAlt
//import compose.icons.fontawesomeicons.solid.CircleNotch
//import compose.icons.fontawesomeicons.solid.ClipboardList
//import compose.icons.fontawesomeicons.solid.Cog
//import compose.icons.fontawesomeicons.solid.Edit
//import compose.icons.fontawesomeicons.solid.InfoCircle
//import compose.icons.fontawesomeicons.solid.MoneyBillAlt
//import compose.icons.fontawesomeicons.solid.Pen
//import compose.icons.fontawesomeicons.solid.Plus
//import compose.icons.fontawesomeicons.solid.ShareAlt
//import compose.icons.fontawesomeicons.solid.Store
//import compose.icons.fontawesomeicons.solid.Users
//import org.koin.androidx.compose.koinViewModel
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavController) {
//    val backgroundColor = colorResource(id = R.color.raspberry)
//    DynamicStatusBar(colorResource(id = R.color.raspberry))
//    // ✅ Define states for search
//    var isSearching by remember { mutableStateOf(false) }
//    var searchQuery by remember { mutableStateOf("") }
//
//    val isHoveredStates = remember {
//        mutableStateMapOf(
//            "Share" to false,
//            "Invite" to false,
//            "Edit" to false
//        )
//    }
//
//    var items by remember { mutableStateOf(listOf(NamePriceItem())) }
//    var showPopup by remember { mutableStateOf(false) }
//    val total = items.sumOf { it.subTotal.toDoubleOrNull() ?: 0.0 } // Calculate total
//
//
//
//    val context = LocalContext.current
//
//    val currentDate = System.currentTimeMillis()
//    val formattedTodayDate = formatDate(currentDate) // Should return "DD-MM-YYYY"
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text("Home", color = Color.White)
//
//                },
//                actions = {
//                    IconButton(onClick = {
//                        navController.navigate(Screen.Settings.route)
//                    }) {
//                        Icon(
//                            imageVector = FontAwesomeIcons.Solid.Cog,
//                            contentDescription = "settings",
//                            tint = Color.White,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = backgroundColor, // dark green
//                    titleContentColor = Color.White,
//                    navigationIconContentColor = Color.White
//                )
//            )
//        },
//
//
//        bottomBar = {
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
//                    .windowInsetsPadding(WindowInsets.navigationBars)
//                    .background(colorResource(id = R.color.white))
//            ) {
//                Button(
//                    onClick = {
//
//                        // ✅ Ensure at least one item exists
//                        if (items.isEmpty()) {
//                            Toast.makeText(
//                                context,
//                                "You must add at least one item before proceeding.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            return@Button
//                        }
//
//
////                            showPopup = true
//                        // ✅ Validation
//                        // Validation: Ensure all fields are filled
//                        val hasEmptyFields = items.any {
//                            it.name.isBlank() ||
//                                    it.price.isBlank() || it.price.toDoubleOrNull() == null ||
//                                    it.quantity.isBlank() || it.quantity.toIntOrNull() == null
//                        }
//
//
//
//                        if (hasEmptyFields) {
//                            Toast.makeText(
//                                context,
//                                "Please fill all required fields (Name, Price, Quantity) before proceeding.",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        } else {
//                            showPopup = true
//                        }
//
//                    },
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
//                        text = "Record Sales",
//                        color = Color.White,
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//                }
//            }
//
//
//
//        }
//
//    ) { paddingValues ->
//        // Scrollable content
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
//                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
//                    top = paddingValues.calculateTopPadding(),
//                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
//                    bottom = paddingValues.calculateBottomPadding()
//                )
//                .verticalScroll(rememberScrollState())
//                .background(colorResource(id = R.color.light_bg_color))
//        ) {
//
//
//            Spacer(modifier = Modifier.height(8.dp)) // space between icon and content
//
//
//            // Title
//            Text(
//                text = "My Sales",
//                modifier = Modifier
//                    .padding(end = 16.dp, start = 16.dp),
//                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
//                color = colorResource(id = R.color.raspberry)
//            )
////                    Spacer(modifier = Modifier.height(8.dp))
//            // Subtitle
//            Text(
//                text = "Record your Sales here both Wholesale and Retail",
//                modifier = Modifier
//                    .padding(end = 16.dp, start = 16.dp),
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Gray
//            )
//
//            // Card 1
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp))
//                    .padding(16.dp)
//            ) {
//                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//
//
//                    items.forEachIndexed { index, item ->
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 8.dp)
//                                .background(
//                                    color = colorResource(id = R.color.papaya_whip),
//                                    shape = RoundedCornerShape(12.dp)
//                                )
//                                .padding(16.dp)
//                        ) {
//                            // Remove Button
//                            Button(
//                                onClick = {
//                                    items = items.toMutableList().also { it.removeAt(index) }
//                                },
//                                colors = ButtonDefaults.buttonColors(
//                                    containerColor = Color.Red,
//                                    contentColor = Color.White
//                                ),
//                                shape = RoundedCornerShape(12.dp),
//                                modifier = Modifier.align(Alignment.End)
//                            ) {
//                                Text("Remove")
//                            }
//
//
//                            // Item Name
//                            OutlinedTextField(
//                                value = item.name,
//                                onValueChange = { newValue ->
//                                    items = items.toMutableList().also {
//                                        it[index] = it[index].copy(name = newValue)
//                                    }
//                                },
//                                label = { Text("Item name *") },
//                                modifier = Modifier.fillMaxWidth(),
//                                colors = OutlinedTextFieldDefaults.colors(
//                                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                    focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                    focusedBorderColor = backgroundColor,
//                                    unfocusedBorderColor = Color.Gray,
//                                    focusedLabelColor = backgroundColor,
//                                    cursorColor = backgroundColor
//                                ),
//                                isError = item.name.isBlank(),
//                                singleLine = true,
//                            )
//                            if (item.name.isBlank()) {
//                                Text(
//                                    text = "Name cannot be empty",
//                                    color = Color.Red,
//                                    fontSize = 12.sp
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            // Price
//                            OutlinedTextField(
//                                value = item.price,
//                                onValueChange = { newValue ->
//                                    val updatedList = items.toMutableList()
//                                    val quantityValue = updatedList[index].quantity.toIntOrNull() ?: 0
//                                    val priceValue = newValue.toDoubleOrNull() ?: 0.0
//                                    val newSubtotal = (priceValue * quantityValue).toString()
//                                    updatedList[index] = updatedList[index].copy(
//                                        price = newValue,
//                                        subTotal = newSubtotal
//                                    )
//                                    items = updatedList
//                                },
//                                label = { Text("Price") },
//                                modifier = Modifier.fillMaxWidth(),
//                                colors = OutlinedTextFieldDefaults.colors(
//                                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                    focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                    focusedBorderColor = backgroundColor,
//                                    unfocusedBorderColor = Color.Gray,
//                                    focusedLabelColor = backgroundColor,
//                                    cursorColor = backgroundColor
//                                ),
//                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                                isError = item.price.isBlank() || item.price.toDoubleOrNull() == null,
//                                singleLine = true,
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            // Quantity
//                            OutlinedTextField(
//                                value = item.quantity,
//                                onValueChange = { newValue ->
//                                    val updatedList = items.toMutableList()
//                                    val priceValue = updatedList[index].price.toDoubleOrNull() ?: 0.0
//                                    val quantityValue = newValue.toIntOrNull() ?: 0
//                                    val newSubtotal = (priceValue * quantityValue).toString()
//                                    updatedList[index] = updatedList[index].copy(
//                                        quantity = newValue,
//                                        subTotal = newSubtotal
//                                    )
//                                    items = updatedList
//                                },
//                                label = { Text("Quantity") },
//                                modifier = Modifier.fillMaxWidth(),
//                                colors = OutlinedTextFieldDefaults.colors(
//                                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                    focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                    focusedBorderColor = backgroundColor,
//                                    unfocusedBorderColor = Color.Gray,
//                                    focusedLabelColor = backgroundColor,
//                                    cursorColor = backgroundColor
//                                ),
//                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                                isError = item.quantity.isBlank() || item.quantity.toIntOrNull() == null,
//                                singleLine = true,
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            // Subtotal (Read-Only)
//                            OutlinedTextField(
//                                value = item.subTotal,
//                                onValueChange = {}, // No editing allowed
//                                label = { Text("Subtotal") },
//                                modifier = Modifier.fillMaxWidth(),
//                                readOnly = true,
//                                colors = OutlinedTextFieldDefaults.colors(
//                                    unfocusedContainerColor = Color.White.copy(alpha = 0.9f),
//                                    focusedContainerColor = Color.White.copy(alpha = 0.95f),
//                                    focusedBorderColor = backgroundColor,
//                                    unfocusedBorderColor = Color.Gray,
//                                    focusedLabelColor = backgroundColor,
//                                    cursorColor = backgroundColor
//                                ),
//                                singleLine = true,
//                            )
//                        }
//                    }
//
//
//                    Button(
//                        onClick = {
//                            items = items + NamePriceItem() // Add a new empty field set
//                        },
//                        modifier = Modifier
//                            .padding(start = 16.dp)
//                            .align(Alignment.Start),
//                        shape = RoundedCornerShape(12.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = colorResource(id= R.color.bittersweet), // Green background
//                            contentColor = Color.White          // White text
//                        )
//                    ) {
//                        Text("Add Item")
//                    }
//
//                }
//
//
//            }
//
//
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 16.dp)
//            ) {
//
//                val icons = listOf(
////                        "Call" to FontAwesomeIcons.Solid.Phone,
//                    "Today Sales" to FontAwesomeIcons.Solid.Store,
//                    "Report" to FontAwesomeIcons.Solid.ClipboardList,
////                    "Cash Sales" to FontAwesomeIcons.Solid.MoneyBillAlt,
//                )
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                        .background(Color.White.copy(alpha = 0.85f), RoundedCornerShape(12.dp)),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    icons.forEach { (label, icon) ->
//
//                        Box(
//                            modifier = Modifier
//                                .padding(8.dp) // Optional: gives some spacing around it
//                                .clip(RoundedCornerShape(12.dp)) // Rounded corners instead of circle
//                                .background(
//                                    if (isHoveredStates[label] == true)
//                                        Color.LightGray.copy(alpha = 0.2f)
//                                    else Color.Transparent
//                                )
//                                .pointerInput(Unit) {
//                                    detectTapGestures(
//                                        onPress = {
//                                            isHoveredStates[label] = true
//                                            tryAwaitRelease()
//                                            isHoveredStates[label] = false
//                                        },
//                                        onTap = {
//                                            when (label) {
//                                                "Today Sales" -> {
//                                                    // Action here
//                                                    navController.navigate("singleSalesReport/$formattedTodayDate")
//                                                }
//                                                "Report" -> {
//                                                    navController.navigate(Screen.Reports.route)
//                                                    Toast.makeText(
//                                                        context,
//                                                        "View Reports",
//                                                        Toast.LENGTH_SHORT
//                                                    ).show()
//                                                }
////                                                "Cash Sales" -> {
////                                                    Toast.makeText(
////                                                        context,
////                                                        "View Cash Sales",
////                                                        Toast.LENGTH_SHORT
////                                                    ).show()
////                                                }
//                                            }
//                                        }
//                                    )
//                                }
//                                .padding(horizontal = 16.dp, vertical = 12.dp), // Adaptive padding
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Column(
//                                horizontalAlignment = Alignment.CenterHorizontally,
//                                verticalArrangement = Arrangement.Center
//                            ) {
//                                Icon(
//                                    imageVector = icon,
//                                    contentDescription = label,
//                                    tint = Color.Black,
//                                    modifier = Modifier.size(24.dp)
//                                )
//                                Spacer(modifier = Modifier.height(4.dp))
//                                Text(
//                                    text = label,
//                                    fontSize = 12.sp,
//                                    color = Color.Black,
//                                    fontWeight = FontWeight.Medium
//                                )
//                            }
//                        }
//
//
//
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//
//
//
//
//        }
//
//    }
//
//
//    if (showPopup) {
//        AddSalePopUp(
//            onDismiss = { showPopup = false;    // Reset the list to only one empty item
//                items = listOf(NamePriceItem()) },
//            total = total,
//            items = items
//        )
//    }
//
//}
//
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(navController = rememberNavController())
//}
//
//
//data class NamePriceItem(
//    val name: String = "",
//    val price: String = "",
//    val subTotal: String = "0",
//    val quantity: String = "1"
//)
//
////data class NamePriceItem(
////    val name: String = "",
////    val price: String = "",
////    val quantity: String = "",
////    val subTotal: String = "0"
////)