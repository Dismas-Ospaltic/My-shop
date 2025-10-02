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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import com.diwtech.myshop.ConsentManager
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.diwtech.myshop.navigation.AppNavHost
import com.diwtech.myshop.utils.requestConsent
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.google.android.gms.ads.*

class MainActivity : ComponentActivity() {
    private lateinit var consentInformation: ConsentInformation
    // In your MainActivity or main Composable
//    var canRequestAds by remember { mutableStateOf(false) }

    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val navController = rememberAnimatedNavController()

            Scaffold(
//                bottomBar = {
//                    BannerAd() // ✅ always here, not recreated per screen
//                    Text(
//                        text = "Settings",
//                        modifier = Modifier.padding(horizontal = 16.dp),
//                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
//                        color = colorResource(id = R.color.dark)
//                    )
//                }
            ) { paddingValues ->
                AppNavHost(navController, Modifier.padding(paddingValues))
            }


            // ✅ Safe to call inside Compose lifecycle
            SideEffect {
                // Ensure full-screen layout
                WindowCompat.setDecorFitsSystemWindows(window, false)
            }
        }

//        ConsentManager.requestConsent(this) {
//            MobileAds.initialize(this) {
//                Log.d("MobileAds", "SDK initialized.")
//            }
//        }


//        // 🔥 Request consent before showing ads
//        ConsentManager.requestConsent(this) {
//            // ✅ Safe: MobileAds.initialize() can be called multiple times
//            MobileAds.initialize(this) {
//                Log.d("MobileAds", "SDK initialized.")
//            }
//
//            // ✅ Add test device IDs (debug only)
//            MobileAds.setRequestConfiguration(
//                RequestConfiguration.Builder()
//                    .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
//                    .build()
//            )
//        }

        // Then handle consent + ads asynchronously
        // In MainActivity's onCreate
        ConsentManager.requestConsent(this) {
            // This is the onSuccess callback
            MobileAds.initialize(this) {
                Log.d("AdsInit", "Mobile Ads SDK initialized.")
            }

            MobileAds.setRequestConfiguration(
                RequestConfiguration.Builder()
                    .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
                    .build()
            )
            // ✅ Update the state to trigger recomposition
//            canRequestAds = true
        }


//        val debugSettings = ConsentDebugSettings.Builder(this)
//            .addTestDeviceHashedId("5EEDD4839E298F38292B35ECD2035259")
//            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//            .build()
////        MobileAds.initialize(this)
//
//        // 🔥 Call consent loader on app launch
////        requestConsent(this)
//
//        consentInformation = UserMessagingPlatform.getConsentInformation(this)
//
//        val params = ConsentRequestParameters.Builder().setConsentDebugSettings(debugSettings).build()
//
//// Requesting an update to consent information should be called on every app launch.
//        consentInformation.requestConsentInfoUpdate(
//            this,
//            params,
//            {
//                // User's consent status successfully updated.
//               UserMessagingPlatform.loadAndShowConsentFormIfRequired(this){ loadAndShowError ->
//                 if (loadAndShowError != null) {
//                     Log.d("ConsentError", loadAndShowError.message.toString())
//                 }
//
//                   //consent form successfully loaded and displayed
//                   if(consentInformation.canRequestAds()){
//
//                       // Initialize the Google Mobile Ads SDK on a background thread.
//                       MobileAds.initialize(this@MainActivity) {
//                           Log.d("MobileAds", "Mobile Ads SDK initialized.")
//                       }
//
//
//                       //to be safe and prevent probable account ban
//                       MobileAds.setRequestConfiguration(
//                           RequestConfiguration.Builder()
//                               .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
//                               .build()
//                       )
//
//                   }
//               }
//
//            },
//            { requestConsentError ->
//                // User's consent status failed to update.
//               Log.d("ConsentError", requestConsentError.message.toString())
//            },
//        )



//        // Ensure full-screen layout
//        WindowCompat.setDecorFitsSystemWindows(window, false)





    }



}



//@Composable
//fun BannerAd(modifier: Modifier = Modifier) {
//    val adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test Banner
//
//    AndroidView(
//        modifier = modifier.fillMaxWidth(),
//        factory = { context ->
//            val adView = AdView(context).apply {
//                this.adUnitId = adUnitId
//
//                // ✅ Use adaptive banner size instead of fixed BANNER
//                val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                    context,
//                    (context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density).toInt()
//                )
//                setAdSize(adSize)
//
//                loadAd(AdRequest.Builder().build())
//            }
//            adView
//        }
//    )
//}
//////////////////////////////////////////////
@Composable
fun BannerAd(modifier: Modifier = Modifier) {
    //val adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test Banner
   val adUnitId = "ca-app-pub-7292527308292656/8005980191" //real banner
    val context = LocalContext.current

    AndroidView(
        modifier = modifier.fillMaxWidth(),
        factory = {
            AdView(context).apply {
                // Set the ad size and unit ID
                this.adUnitId = adUnitId
                val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    context,
                    (context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density).toInt()
                )
                setAdSize(adSize)

                // ✅ Set up the AdLoadCallback
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        super.onAdLoaded()
                        Log.d("BannerAd", "Ad successfully loaded.")
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        super.onAdFailedToLoad(error)
                        // Ad failed to load. You can log the error for debugging.
                        // You could also implement a retry mechanism here if desired.
                        Log.e("BannerAd", "Ad failed to load: ${error.message}")
                    }
                }

                // Load the ad
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}


/////////////////////////
//@Composable
//fun BannerAd(modifier: Modifier = Modifier) {
//    val context = LocalContext.current
//
//    // ✅ Remember the AdView so it survives recomposition/navigation
//    val adView = remember {
//        AdView(context).apply {
//            adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test Banner ID
//            setAdSize(
//                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                    context,
//                    (context.resources.displayMetrics.widthPixels /
//                            context.resources.displayMetrics.density).toInt()
//                )
//            )
//        }
//    }
//
//    // Load ad once when composable enters
//    LaunchedEffect(Unit) {
//        adView.loadAd(AdRequest.Builder().build())
//    }
//
//    AndroidView(
//        modifier = modifier,
//        factory = { adView }
//    )
//}






//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.view.WindowCompat
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.diwtech.myshop.navigation.AppNavHost
//import com.google.accompanist.navigation.animation.rememberAnimatedNavController
//import com.google.android.gms.ads.*
//
//class MainActivity : ComponentActivity() {
//
//    @OptIn(ExperimentalAnimationApi::class)
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Ensure the app draws behind the system bars
//        WindowCompat.setDecorFitsSystemWindows(window, false)
//
//        setContent {
//
//                // 1. Declare your state variable INSIDE the composable scope.
//                var canRequestAds by remember { mutableStateOf(false) }
//
//                // 2. Use LaunchedEffect to run the consent request when the UI first appears.
//                LaunchedEffect(Unit) {
//                    ConsentManager.requestConsent(this@MainActivity) {
//                        // This is the success callback
//                        MobileAds.initialize(this@MainActivity) {}
//                        MobileAds.setRequestConfiguration(
//                            RequestConfiguration.Builder()
//                                .setTestDeviceIds(listOf("5EEDD44839E298F38292B35ECD2035259")) // Your test device ID
//                                .build()
//                        )
//                        // 3. Update the state. Compose will automatically
//                        //    recompose the UI where `canRequestAds` is used.
//                        canRequestAds = true
//                    }
//                }
//
////                val navController = rememberNavController()
//            val navController = rememberAnimatedNavController()
//                Scaffold(
//                    // 4. Use the state to conditionally show your UI.
//                    //    The BannerAd is placed in the bottom bar slot.
////                    bottomBar = {
////                        if (canRequestAds) {
////                            BannerAd()
////                        }
////                    }
//                ) { innerPadding ->
//                    // Your NavHost will now correctly adjust to the ad being present or not.
//                    AppNavHost(navController,
//                        canRequestAds, // 👈 Pass state down
//                 Modifier.padding(innerPadding))
//                }
//
//        }
//    }
//}
//
//
///**
// * The BannerAd composable with load callbacks.
// */
//@Composable
//fun BannerAd(modifier: Modifier = Modifier) {
//    val adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test Banner ID
//    val context = LocalContext.current
//
//    AndroidView(
//        modifier = modifier.fillMaxWidth(),
//        factory = {
//            AdView(context).apply {
//                this.adUnitId = adUnitId
//                val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
//                    context,
//                    (context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density).toInt()
//                )
//                setAdSize(adSize)
//
//                // Set up the AdLoadCallback
//                adListener = object : AdListener() {
//                    override fun onAdLoaded() {
//                        super.onAdLoaded()
//                        Log.d("BannerAd", "Ad successfully loaded.")
//                    }
//
//                    override fun onAdFailedToLoad(error: LoadAdError) {
//                        super.onAdFailedToLoad(error)
//                        Log.e("BannerAd", "Ad failed to load: ${error.message}")
//                    }
//                }
//                // Load the ad
//                loadAd(AdRequest.Builder().build())
//            }
//        }
//    )
//}
//




