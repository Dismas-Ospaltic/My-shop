package com.diwtech.myshop.screens





import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.diwtech.myshop.R
import com.diwtech.myshop.BannerAd
import androidx.navigation.NavController
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(navController: NavController) {
    val backgroundColor = colorResource(id = R.color.prussian_blue)
    val customColor = colorResource(id = R.color.colorful)

    val context = LocalContext.current


    Scaffold(
        bottomBar = {


            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars)
            ) {
                // ✅ Show banner ad
                BannerAd(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp)
//                        .padding(4.dp) // optional
                )


            }

        }

    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
//                    start = paddingValues.calculateStartPadding(LocalLayoutDirection.current) + 12.dp,
//                    top = paddingValues.calculateTopPadding(),
//                    end = paddingValues.calculateEndPadding(LocalLayoutDirection.current) + 12.dp,
//                    bottom = paddingValues.calculateBottomPadding()
//                )
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
//                text = "About",
//                modifier = Modifier.padding(horizontal = 16.dp),
//                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
//                color = colorResource(id = R.color.dark)
//            )
//
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Box(modifier = Modifier.fillMaxWidth()) {
//                Column(
////
//                                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//
//
//                    // App Logo (replace with your drawable)
//                    Icon(
//                        painter = painterResource(id = R.drawable.app_icon),
//                        contentDescription = "App Logo",
//                        tint = colorResource(id=R.color.prussian_blue),
//                        modifier = Modifier.size(80.dp)
//                    )
//
//                    // App Name
//                    Text(
//                        text = "My Shop App",
//                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//
//                    // Version
//                    Text(
//                        text = "Version 1.2.0",
//                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
//                    )
//
//
//                            Text(
//                                text = "App Preview",
//                                style = MaterialTheme.typography.bodyLarge,
//                                textAlign = TextAlign.Center
//                            )
//                    // Description
//                    Text(
//                        text = "This app makes it easy to manage products, " +
//                                "track sales, and view clear" +
//                                " sales reports — all in one simple dashboard.",
//                        style = MaterialTheme.typography.bodyMedium,
//                        textAlign = TextAlign.Center
//                    )
//
////                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
//                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
//                    // Developer Info
//                    Text(
//                        text = "Developed by DiWtech Solutions",
//                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
//                    )
//                    Text(
//                        text = "Contact: wanyaladismas5@gmail,com",
//                        style = MaterialTheme.typography.bodyMedium
//                    )
//
////                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
//                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
//                    // Legal Links
//                    OutlinedButton(
//                        onClick = {
//                            val intent = Intent(
//                                Intent.ACTION_VIEW,
//                                "https://diwtech.github.io/MyShop-App/privacy_policy.html".toUri()
//                            )
//                            context.startActivity(intent)
//                        },
//                        modifier = Modifier.fillMaxWidth(),
//                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//                        colors = ButtonDefaults.outlinedButtonColors(
//                            contentColor = MaterialTheme.colorScheme.primary
//                        )
//                    ) {
//                        Text("Privacy Policy")
//                    }
//
//
//                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
//                    // Credits & Acknowledgements
//                    Text(
//                        text = "Credits & Acknowledgements",
//                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
//                        modifier = Modifier.align(Alignment.Start)
//                    )
//
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        Text("• Special thanks to testers and contributors")
//                    }
//
//
//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(16.dp)
//                    ) {
//
//                        Text(
//                            text = "Intellectual Property Notice",
//                            style = MaterialTheme.typography.titleMedium,
//                            fontWeight = FontWeight.Bold
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Text(
//                            text = "Third-party assets used in My Shop app are sourced from publicly available open-source or free-to-use repositories. All trademarks, logos, and brand names appearing in the app are the property of their respective owners. Use of these assets follows their respective intellectual property guidelines.",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Text(
//                            text = "The developer of My Shop app does not claim ownership of third-party assets used with attribution and fully respects all copyright and intellectual property rights.",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
//                    }
//                }
//            }
//        }

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

            // Back Button
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

            Spacer(modifier = Modifier.height(8.dp))

            // Title
            Text(
                text = "About",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.dark)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // App Logo
                    Icon(
                        painter = painterResource(id = R.drawable.app_icon),
                        contentDescription = "App Logo",
                        tint = colorResource(id = R.color.prussian_blue),
                        modifier = Modifier.size(80.dp)
                    )

                    // App Name
                    Text(
                        text = "My Shop App",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    // Version
                    Text(
                        text = "Version 1.2.0",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )

                    // Preview Title
                    Text(
                        text = "App Preview",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )

                    // Description
                    Text(
                        text = "This app makes it easy to manage products, " +
                                "track sales, and view clear sales reports — all in one simple dashboard.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)

                    // Developer Info
                    Text(
                        text = "Developed by DiWtech Solutions",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )

                    Text(
                        text = "Contact: wanyaladismas5@gmail.com",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)

                    // Privacy Policy Button
                    OutlinedButton(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                "https://diwtech.github.io/MyShop-App/privacy_policy.html".toUri()
                            )
                            context.startActivity(intent)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("Privacy Policy")
                    }

                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)

                    // Credits
                    Text(
                        text = "Credits & Acknowledgements",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("• Special thanks to testers and contributors")
                    }

                    // Intellectual Property Section
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Text(
                            text = "Intellectual Property Notice",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Third-party assets used in My Shop app are sourced from publicly available open-source or free-to-use repositories. All trademarks, logos, and brand names appearing in the app are the property of their respective owners. Use of these assets follows their respective intellectual property guidelines.",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "The developer of My Shop app does not claim ownership of third-party assets used, and fully respects all copyright and intellectual property rights.",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }

    }




}









