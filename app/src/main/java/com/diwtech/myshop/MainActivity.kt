package com.diwtech.myshop



import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.initialization.InitializationStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        MobileAds.initialize(this)

        // Initialize the Mobile Ads SDK.
//        MobileAds.initialize(this) {}


        // Initialize the Google Mobile Ads SDK on a background thread.
        MobileAds.initialize(this@MainActivity) {
            Log.d("MobileAds", "Mobile Ads SDK initialized.")
        }


        //to be safe and prevent probable account ban
        MobileAds.setRequestConfiguration(
            RequestConfiguration.Builder()
                .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
                .build()
        )


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
//fun BannerAd(modifier: Modifier = Modifier) {
//    val adUnitId = "ca-app-pub-3940256099942544/6300978111" // ✅ Test Banner
//
//    AndroidView(
//        modifier = modifier
//            .fillMaxWidth()
//            .height(50.dp), // 👈 ensure height is fixed
//        factory = { context ->
//            AdView(context).apply {
//                setAdSize(AdSize.BANNER)
//                this.adUnitId = adUnitId
//                loadAd(AdRequest.Builder().build())
//            }
//        }
//    )
//}

@Composable
fun BannerAd(modifier: Modifier = Modifier) {
    val adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test Banner

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = { context ->
            val adView = AdView(context).apply {
                this.adUnitId = adUnitId

                // ✅ Use adaptive banner size instead of fixed BANNER
                val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    context,
                    (context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density).toInt()
                )
                setAdSize(adSize)

                loadAd(AdRequest.Builder().build())
            }
            adView
        }
    )
}




/////////////////////////





