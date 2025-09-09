package com.ditech.myshop.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ditech.myshop.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ditech.myshop.model.ProductCategories
import com.ditech.myshop.navigation.Screen
import com.ditech.myshop.utils.DynamicStatusBar
import com.ditech.myshop.utils.loadProductCategoriesFromAssets
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.ThumbsUp
import compose.icons.fontawesomeicons.regular.TrashAlt
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.CircleNotch
import compose.icons.fontawesomeicons.solid.DollarSign
import compose.icons.fontawesomeicons.solid.Pen
import compose.icons.fontawesomeicons.solid.Plus
import compose.icons.fontawesomeicons.solid.PlusCircle
import compose.icons.fontawesomeicons.solid.Search
import compose.icons.fontawesomeicons.solid.ShareAlt
import kotlin.text.get


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageProductScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    DynamicStatusBar(colorResource(id = R.color.white))
    val buttonColor = colorResource(id = R.color.prussian_blue) // Button color
    val textColor = colorResource(id = R.color.white) // Text color
    val searchQuery = remember { mutableStateOf("") }


    val context = LocalContext.current

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    var selectedNotes by remember { mutableStateOf("") }

    val buttons = listOf(
        ButtonItem(
            label = "Add Product",
            icon = {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.PlusCircle,
                    contentDescription = "add icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            },
            onClick = {
             navController.navigate(Screen.AddProduct.route)

            }
        ),
        ButtonItem(
            label = "Low Stock",
            icon = {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.DollarSign,
                    contentDescription = "dollar icon",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            },
            onClick = {


            }
        )
    )

    val columns = if (LocalConfiguration.current.screenWidthDp < 600) 2 else 3 // Responsive grid


    Scaffold(

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
                text = "Manage Products",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id=R.color.dark)
            )
//                    Spacer(modifier = Modifier.height(8.dp))
            // Subtitle
            Text(
                text = "add new products, manage stock and manage products",
                modifier = Modifier
                    .padding(end = 16.dp, start = 16.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column( verticalArrangement = Arrangement.spacedBy(12.dp) ){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp)
                        .background(colorResource(id = R.color.dark), shape = RoundedCornerShape(4.dp)) // Use your background color
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp) // Spacing between rows
                    ) {
                        Text(text = "Manage Products",
                            color = colorResource(id = R.color.white),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(8.dp)
                        )

                        buttons.chunked(columns).forEach { rowButtons ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp) // Spacing between buttons
                            ) {
                                rowButtons.forEach { button ->
                                    Box(
                                        modifier = Modifier
                                            .weight(1f) // Distribute buttons evenly
                                            .padding(8.dp)
                                    ) {
//                                    CustomButton(button, buttonColor, textColor)// Pass your button data
                                        CustomOutlinedButton(button, buttonColor, textColor)
                                    }
                                }

                                // Fill remaining space if the last row has fewer buttons
                                repeat(columns - rowButtons.size) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }



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



                repeat(10) { index ->


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                            .clickable { /* Handle click */
                                showSheet = true
                                selectedNotes = "Note $index"
                            },
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            // Top Row: Amount & Category
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "food",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = colorResource(id = R.color.gray01)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "2025-09-09",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }

                                Text(
                                    text = "Ksh. 200",
                                    fontSize = 22.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.light_green)
                                )
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            // Description
                            Text(
                                text = "lunch",
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.dark),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Bottom Row: Payment mode with icon
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.DollarSign,
                                    contentDescription = "money check",
                                    tint = colorResource(id = R.color.light_green),
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Payment: cash",
                                    fontSize = 14.sp,
                                    color = colorResource(id = R.color.gray01)
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
                Text("Additional Notes", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(selectedNotes)
                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.CircleNotch,
                            contentDescription = "update",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "update Progress", fontSize = 16.sp)
                    }



                    // Edit Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Pen,
                            contentDescription = "Edit",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Edit", fontSize = 16.sp)
                    }



                    // Delete Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.TrashAlt,
                            contentDescription = "Delete",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Delete", fontSize = 16.sp)
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {


                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.ShareAlt,
                            contentDescription = "share",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Share Watchlist", fontSize = 16.sp)
                    }


                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {


                            }
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Regular.ThumbsUp,
                            contentDescription = "Mark as complete",
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "Mark as complete", fontSize = 16.sp)
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
}




@Composable
fun CustomOutlinedButton(
    button: ButtonItem,
    buttonColor: Color,
    textColor: Color
) {
    OutlinedButton(
        onClick = button.onClick,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.5.dp, buttonColor),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = textColor
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            button.icon() // 👈 directly invoke the composable icon
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = button.label,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }




}




data class ButtonItem(
    val label: String,
    val icon: @Composable () -> Unit,
    val onClick: () -> Unit
)


@Preview(showBackground = true)
@Composable
fun ManageProductScreenPreview() {
    ManageProductScreen(navController = rememberNavController())
}


