package com.diwtech.myshop

//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.st11.myshop.ui.theme.MyShopTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyShopTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyShopTheme {
//        Greeting("Android")
//    }
//}

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.diwtech.myshop.navigation.AppNavHost
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        MobileAds.initialize(this)


        CoroutineScope(Dispatchers.IO).launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@MainActivity) {}
        }
        // Ensure full-screen layout
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val navController = rememberAnimatedNavController()


            Scaffold(

            ) { paddingValues ->
                AppNavHost(navController, Modifier.padding(paddingValues))
            }

//            MobileAds.initialize(this@MainActivity) {}
//
//            BannerAd(modifier = Modifier.fillMaxSize(), adId = "ca-app-pub-3940256099942544/9214589741")
        }
    }
}

//@Composable
//fun BannerAdView() {
//    AndroidView(
//        factory = { context ->
//            com.google.android.gms.ads.AdView(context).apply {
//                setAdSize(com.google.android.gms.ads.AdSize.BANNER)
//                adUnitId = "ca-app-pub-3940256099942544/9214589741" // Test banner ID
//                loadAd(com.google.android.gms.ads.AdRequest.Builder().build())
//            }
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//}

@Composable
fun BannerAdView() {
    AndroidView(
        factory = { context ->
            com.google.android.gms.ads.AdView(context).apply {
                setAdSize(com.google.android.gms.ads.AdSize.BANNER)
                adUnitId = "ca-app-pub-3940256099942544/9214589741" // ✅ Test Banner ID
                loadAd(com.google.android.gms.ads.AdRequest.Builder().build())
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp) // Standard banner height
    )
}


//@Composable
//fun BannerAd(modifier: Modifier, adId: String){
//   Column(modifier = modifier) {
//       Spacer(modifier = Modifier.size(16.dp))
//       AndroidView(
//           modifier = Modifier.fillMaxWidth(), //.height(50.dp),
//           factory = { context ->
//               AdView(context).apply {
//                   setAdSize(AdSize.BANNER)
//                   adUnitId = adId
//                   //request an ad
//                   loadAd(AdRequest.Builder().build())
//               }
//           }
//       )
//   }
//}


