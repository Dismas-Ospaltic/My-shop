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


//    val creditIcons = listOf(
//        "App icon was designed by kerismaker from Flaticon",
//
//        "App Screen image icons:",
//        "-No data Placeholder icon image designed by Roundicons Premium from Flaticon",
//        "-No Search data placeholder icon image designed by Roundicons Premium from Flaticon",
//    )

    val creditIcons = listOf(
        "App icon designed by kerismaker from Flaticon",
        "App screen image icons:",
        "- No data placeholder icon designed by Roundicons Premium from Flaticon",
        "- No search data placeholder icon designed by Roundicons Premium from Flaticon"
    )


    Scaffold(

    ) { paddingValues ->
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

//            Text(
//                text = "Manage your settings",
//                modifier = Modifier.padding(horizontal = 16.dp),
//                style = MaterialTheme.typography.bodyMedium,
//                color = Color.Gray
//            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
//
                                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {


                    // App Logo (replace with your drawable)
                    Icon(
                        painter = painterResource(id = R.drawable.store),
                        contentDescription = "App Logo",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(80.dp)
                    )

                    // App Name
                    Text(
                        text = "My Shop App",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )

                    // Version
                    Text(
                        text = "Version 1.0.0",
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                    )

                    // Description
                    Text(
                        text = "This app makes it easy to manage products, " +
                                "track sales, and view clear" +
                                " sales reports — all in one simple dashboard.",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

//                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                    // Developer Info
                    Text(
                        text = "Developed by DiWtech Solutions",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium)
                    )
                    Text(
                        text = "Contact: wanyaladismas5@gmail,com",
                        style = MaterialTheme.typography.bodyMedium
                    )

//                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                    // Legal Links
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

//                    OutlinedButton(
//                        onClick = {  },
//                        modifier = Modifier.fillMaxWidth(),
//                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
//                        colors = ButtonDefaults.outlinedButtonColors(
//                            contentColor = MaterialTheme.colorScheme.primary
//                        )
//                    ) {
//                        Text("Terms & Conditions")
//                    }

//                    HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                    HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                    // Credits & Acknowledgements
                    Text(
                        text = "Credits & Acknowledgements",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.align(Alignment.Start)
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
//                        Text("• Icons provided by Material Icons")
//                        Text("• Built with Jetpack Compose")
//                        Text("• Thanks to the open-source community for libraries and tools")

                        creditIcons.forEach { credit ->
                            Text(
                                text = credit,
                                style = if (credit.endsWith(":"))
                                    MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                                else
                                    MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }

                        // ✅ External link
                        Text(
                            text = "Visit Flaticon: www.flaticon.com",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Blue,
                                fontWeight = FontWeight.SemiBold
                            ),
                            modifier = Modifier
                                .clickable {
                                    val url = "https://www.flaticon.com"
                                    val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                                    context.startActivity(intent)
                                }
                                .padding(vertical = 4.dp)
                        )

                        Text("• Special thanks to testers and contributors")
                    }

                }
            }
        }
    }




}









