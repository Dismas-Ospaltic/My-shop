//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.annotation.RequiresApi
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.view.WindowCompat
//import com.diwtech.myshop.ConsentManager
//import com.diwtech.myshop.navigation.AppNavHost
//import com.google.accompanist.navigation.animation.rememberAnimatedNavController
//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.AdSize
//import com.google.android.gms.ads.AdView
//import com.google.android.gms.ads.LoadAdError
//import com.google.android.gms.ads.MobileAds
//import com.google.android.gms.ads.RequestConfiguration
//import com.google.android.ump.ConsentInformation
//
//class MainActivity : ComponentActivity() {
//    private lateinit var consentInformation: ConsentInformation
//    // In your MainActivity or main Composable
////    var canRequestAds by remember { mutableStateOf(false) }
//
//    @RequiresApi(Build.VERSION_CODES.S)
//    @OptIn(ExperimentalAnimationApi::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        // In MainActivity's onCreate
//        ConsentManager.requestConsent(this) {
//            // This is the onSuccess callback
//            MobileAds.initialize(this) {
//                Log.d("AdsInit", "Mobile Ads SDK initialized.")
//            }
//
//            MobileAds.setRequestConfiguration(
//                RequestConfiguration.Builder()
//                    .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
//                    .build()
//            )
//            // ✅ Update the state to trigger recomposition
////            canRequestAds = true
//        }
//
//
//
//        // Ensure full-screen layout
////        WindowCompat.setDecorFitsSystemWindows(window, false)
//
//        setContent {
//            val navController = rememberAnimatedNavController()
//
//            Scaffold(
//
//            ) { paddingValues ->
//                AppNavHost(navController, Modifier.padding(paddingValues))
//            }
//            // ✅ Safe to call inside Compose lifecycle
//            SideEffect {
//                WindowCompat.setDecorFitsSystemWindows(window, false)
//            }
//
//        }
//    }
//
//
//}
//
//
//@Composable
//fun BannerAd(modifier: Modifier = Modifier) {
//    val adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test Banner
//    val context = LocalContext.current
//
//    AndroidView(
//        modifier = modifier.fillMaxWidth(),
//        factory = {
//            AdView(context).apply {
//                // Set the ad size and unit ID
//                this.adUnitId = adUnitId
//                val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                    context,
//                    (context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density).toInt()
//                )
//                setAdSize(adSize)
//
//                // ✅ Set up the AdLoadCallback
//                adListener = object : AdListener() {
//                    override fun onAdLoaded() {
//                        super.onAdLoaded()
//                        Log.d("BannerAd", "Ad successfully loaded.")
//                    }
//
//                    override fun onAdFailedToLoad(error: LoadAdError) {
//                        super.onAdFailedToLoad(error)
//                        // Ad failed to load. You can log the error for debugging.
//                        // You could also implement a retry mechanism here if desired.
//                        Log.e("BannerAd", "Ad failed to load: ${error.message}")
//                    }
//                }
//
//                // Load the ad
//                loadAd(AdRequest.Builder().build())
//            }
//        }
//    )
//}

//@RequiresApi(Build.VERSION_CODES.S)
//@OptIn(ExperimentalAnimationApi::class)
//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//
//    // Request consent
//    ConsentManager.requestConsent(this) {
//        MobileAds.initialize(this) { Log.d("AdsInit", "Mobile Ads SDK initialized.") }
//        MobileAds.setRequestConfiguration(
//            RequestConfiguration.Builder()
//                .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
//                .build()
//        )
//    }
//
//    setContent {
//        val navController = rememberAnimatedNavController()
//        Scaffold { paddingValues ->
//            AppNavHost(navController, Modifier.padding(paddingValues))
//        }
//
//        // ✅ Safe to call inside Compose lifecycle
//        SideEffect {
//            WindowCompat.setDecorFitsSystemWindows(window, false)
//        }
//    }
//}
