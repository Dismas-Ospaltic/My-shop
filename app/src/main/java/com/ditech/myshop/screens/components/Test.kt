//package com.st11.expensetracker.screens
//
//import android.Manifest
//import android.app.Activity
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.os.Build
//import android.widget.Toast
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.hoverable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import androidx.navigation.compose.rememberNavController
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.platform.LocalLayoutDirection
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.core.content.ContextCompat
//import androidx.core.view.WindowInsetsCompat
//import androidx.core.view.WindowInsetsControllerCompat
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.st11.expensetracker.R
//import com.st11.expensetracker.data.datastore.UserTimeData
//import com.st11.expensetracker.screens.components.ExpensePopup
//import com.st11.expensetracker.utils.DynamicStatusBar
//import com.st11.expensetracker.utils.formatDateToReadable
//import com.st11.expensetracker.viewmodel.CurrencyViewModel
//import com.st11.expensetracker.viewmodel.ExpenseViewModel
//import com.st11.expensetracker.viewmodel.IntervalViewModel
//import com.st11.expensetracker.viewmodel.MainViewModel
//import com.st11.expensetracker.viewmodel.OnboardingViewModel
//import compose.icons.FontAwesomeIcons
//import compose.icons.fontawesomeicons.Regular
//import compose.icons.fontawesomeicons.Solid
//import compose.icons.fontawesomeicons.solid.Search
//import compose.icons.fontawesomeicons.solid.Users
//import org.koin.androidx.compose.getViewModel
//import org.koin.androidx.compose.koinViewModel
//import com.st11.expensetracker.utils.requestNotificationPermission
//import compose.icons.fontawesomeicons.solid.DollarSign
//import compose.icons.fontawesomeicons.solid.MoneyCheck
//import compose.icons.fontawesomeicons.solid.Plus
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen(navController: NavController) {
//
//    val backgroundColor = colorResource(id = R.color.light_green)
//    DynamicStatusBar(backgroundColor)
//
//    val viewModel: MainViewModel = koinViewModel()
//    // Example: Schedule reminder on first launch or after login
//
//    val intervalViewModel: IntervalViewModel = koinViewModel()
////    val interval by intervalViewModel.userTimeInterval.collectAsState(initial = 1L)
//
//    val intervalData by intervalViewModel.userTimeInterval.collectAsState(
//        initial = UserTimeData(userTimeInterval = 1L) //LATER CHANGE TO 1L
//    )
//    val interval = intervalData.userTimeInterval
//    val hasWorkerStated by intervalViewModel.hasWorkerStarted.collectAsState(initial = false)
//    val context = LocalContext.current
//    val activity = context as? Activity
//    var showDialog by remember { mutableStateOf(false) } // State to control popup visibility
//
//
//
//
//
//
//
//
//    LaunchedEffect(hasWorkerStated, interval) {
//
//        if (!hasWorkerStated) {
//            viewModel.startReminderWorker(interval)
//            intervalViewModel.markWorkerStarted()
//        }
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (activity != null) {
//                requestNotificationPermission(activity)
//            }
//        }
//
//
//    }
//
//    val searchQuery = remember { mutableStateOf("") }
//
//    val expenseViewModel: ExpenseViewModel = koinViewModel()
//    val expenses by expenseViewModel.debts.collectAsState()
//
//    val currencyViewModel: CurrencyViewModel = koinViewModel()
//    val currency by currencyViewModel.userData.collectAsState()
//
////    val createIdentityViewModel: CreateIdentityViewModel = getViewModel()
////    val userData by createIdentityViewModel.userData.collectAsState()
//
//
//    // ✅ **Filter the list based on search query**
//    val filteredExpenses = expenses.filter {
//        it.expenseDescription.contains(searchQuery.value, ignoreCase = true) ||
//                it.expenseDate.contains(searchQuery.value, ignoreCase = true) ||
//                it.expenseCategory.contains(searchQuery.value, ignoreCase = true)
//    }
//
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("My Expenses", color = Color.White) },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = backgroundColor,
//                    titleContentColor = Color.White,
//                    navigationIconContentColor = Color.White
//                )
//            )
//        }
//    ) { paddingValues ->
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
////                 paddingValues
//                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current),
//                    top = paddingValues.calculateTopPadding(),
//                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current),
//                    bottom = paddingValues.calculateBottomPadding() + 80.dp
//                )
//                .verticalScroll(rememberScrollState())
//                .background(colorResource(id = R.color.light_bg_color))
//        ) {
//            Spacer(modifier = Modifier.height(10.dp))
//
//            Box(
//                modifier = Modifier.fillMaxWidth(),
//                contentAlignment = Alignment.Center
//            ) {
//
//
//            }
//
//            // Search Field
//            TextField(
//                value = searchQuery.value,
//                onValueChange = { searchQuery.value = it },
//                placeholder = { Text(text = "Search...") },
//                leadingIcon = {
//
//                    Icon(
//                        imageVector = FontAwesomeIcons.Solid.Search,
//                        contentDescription = "Search Icon",
//                        tint = Color.Gray,
//                        modifier = Modifier.size(24.dp)
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//                    .clip(RoundedCornerShape(12.dp))
//                    .background(Color.White),
//                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color.Transparent,
//                    unfocusedContainerColor = Color.Transparent,
//                    disabledContainerColor = Color.Transparent,
//                    cursorColor = Color.Black,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent
//                ),
//                singleLine = true
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//            // ✅ Show "No Data Available" if the list is empty initially or after filtering
//            if (expenses.isEmpty()) {
//                // No data available at the initial display
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "No Data Available",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Gray
//                    )
//
//                    // No data available after search
//
//                }
//            }else if (filteredExpenses.isEmpty()){
//                // No data available after search
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "No Results Found",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color.Gray
//                    )
//                }
//            }else{
//
//
//
////                    repeat(10) { index ->
////                items(filteredPeople) { index, person ->
//                for (index in filteredExpenses.indices) {
////                        Card(
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .padding(8.dp)
////                                .clickable {
////
////                                },
////                            shape = RoundedCornerShape(12.dp),
////                            colors = CardDefaults.cardColors(containerColor = Color.White)
////                        ) {
////                            Text(text = "${currency.userCurrency} ${expenses[index].expenseAmount}", fontWeight = FontWeight.Bold, fontSize = 24.sp,
////                                modifier = Modifier.align(Alignment.End)
////                                    .padding(8.dp)
////                            ,
////                                color = colorResource(id = R.color.light_green)
////                            )
////                            Spacer(modifier = Modifier.height(4.dp))
////                            Text(text = expenses[index].expenseCategory, fontWeight = FontWeight.Bold, fontSize = 12.sp,
////                                modifier = Modifier.align(Alignment.End)
////                                    .padding(end = 8.dp)
////                                ,
////                                color = colorResource(id = R.color.light_green)
////                            )
////                            Row(
////                                modifier = Modifier
////                                    .fillMaxWidth()
////                                    .padding(16.dp),
////                                verticalAlignment = Alignment.CenterVertically
////                            ) {
////                                Column(modifier = Modifier.weight(1f)) {
////                                    Text(text = expenses[index].expenseDescription, color = colorResource(id= R.color.gray01))
////                                    formatDateToReadable(expenses[index].expenseDate)?.let { Text(text = it) }
////                                    Text(text = "payment mode: " + expenses[index].paymentMode, color = colorResource(id= R.color.dark))
////                                }
////                            }
////                        }
//                    Card(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 12.dp, vertical = 8.dp)
//                            .clickable { /* Handle click */ },
//                        shape = RoundedCornerShape(16.dp),
//                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
//                        colors = CardDefaults.cardColors(containerColor = Color.White)
//                    ) {
//                        Column(modifier = Modifier.padding(16.dp)) {
//
//                            // Top Row: Amount & Category
//                            Row(
//                                modifier = Modifier.fillMaxWidth(),
//                                horizontalArrangement = Arrangement.SpaceBetween,
//                                verticalAlignment = Alignment.CenterVertically
//                            ) {
//                                Column {
//                                    Text(
//                                        text = expenses[index].expenseCategory,
//                                        fontSize = 14.sp,
//                                        fontWeight = FontWeight.SemiBold,
//                                        color = colorResource(id = R.color.gray01)
//                                    )
//                                    Spacer(modifier = Modifier.height(4.dp))
//                                    Text(
//                                        text = formatDateToReadable(expenses[index].expenseDate) ?: "",
//                                        fontSize = 12.sp,
//                                        color = Color.Gray
//                                    )
//                                }
//
//                                Text(
//                                    text = "${currency.userCurrency} ${expenses[index].expenseAmount}",
//                                    fontSize = 22.sp,
//                                    fontWeight = FontWeight.Bold,
//                                    color = colorResource(id = R.color.light_green)
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.height(12.dp))
//
//                            // Description
//                            Text(
//                                text = expenses[index].expenseDescription,
//                                fontSize = 16.sp,
//                                color = colorResource(id = R.color.dark),
//                                maxLines = 2,
//                                overflow = TextOverflow.Ellipsis
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            // Bottom Row: Payment mode with icon
//                            Row(verticalAlignment = Alignment.CenterVertically) {
//                                Icon(
//                                    imageVector = FontAwesomeIcons.Solid.DollarSign,
//                                    contentDescription = "money check",
//                                    tint = colorResource(id = R.color.light_green),
//                                    modifier = Modifier.size(18.dp)
//                                )
//                                Spacer(modifier = Modifier.width(6.dp))
//                                Text(
//                                    text = "Payment: ${expenses[index].paymentMode}",
//                                    fontSize = 14.sp,
//                                    color = colorResource(id = R.color.gray01)
//                                )
//                            }
//                        }
//                    }
//
//                }
//
//            }
//
//        }
//
//    }
//
//    // Show the Payment Popup if showDialog is true
//    if (showDialog) {
//        ExpensePopup(onDismiss = { showDialog = false })
//
//    }
//
//}
//
//
//
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(navController = rememberNavController())
//}