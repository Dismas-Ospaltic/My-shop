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
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.*

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
//
//        setContent {
//            val navController = rememberNavController()
//            Scaffold(
//            ) { paddingValues ->
//                AppNavHost(navController, Modifier.padding(paddingValues))
//            }
//
//
//            // ✅ Safe to call inside Compose lifecycle
//            SideEffect {
//                // Ensure full-screen layout
//                WindowCompat.setDecorFitsSystemWindows(window, false)
//            }
//        }
//
////        ConsentManager.requestConsent(this) {
////            MobileAds.initialize(this) {
////                Log.d("MobileAds", "SDK initialized.")
////            }
////        }
//
//
////        // 🔥 Request consent before showing ads
////        ConsentManager.requestConsent(this) {
////            // ✅ Safe: MobileAds.initialize() can be called multiple times
////            MobileAds.initialize(this) {
////                Log.d("MobileAds", "SDK initialized.")
////            }
////
////            // ✅ Add test device IDs (debug only)
////            MobileAds.setRequestConfiguration(
////                RequestConfiguration.Builder()
////                    .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259"))
////                    .build()
////            )
////        }
//
//        // Then handle consent + ads asynchronously
//        // In MainActivity's onCreate
//        ConsentManager.requestConsent(this) {
//            // This is the onSuccess callback
//            MobileAds.initialize(this) {
//                Log.d("AdsInit", "Mobile Ads SDK initialized.")
//            }
//
//            MobileAds.setRequestConfiguration(
//                RequestConfiguration.Builder()
//                    .setTestDeviceIds(listOf(
//                        "5ED29E53360FA1E3D4AB444DB9B0D7DB"
//                    ))
//                    .build()
//            )
//            // ✅ Update the state to trigger recomposition
////            canRequestAds = true
//        }
//
///////////////////////////////////////////
////        val debugSettings = ConsentDebugSettings.Builder(this)
////            .addTestDeviceHashedId("5ED29E53360FA1E3D4AB444DB9B0D7DB")
////            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
////            .build()
//////        MobileAds.initialize(this)
////
////        // 🔥 Call consent loader on app launch
////        requestConsent(this)
////
////        consentInformation = UserMessagingPlatform.getConsentInformation(this)
////
////        val params = ConsentRequestParameters.Builder().setConsentDebugSettings(debugSettings).build()
////
////// Requesting an update to consent information should be called on every app launch.
////        consentInformation.requestConsentInfoUpdate(
////            this,
////            params,
////            {
////                // User's consent status successfully updated.
////               UserMessagingPlatform.loadAndShowConsentFormIfRequired(this){ loadAndShowError ->
////                 if (loadAndShowError != null) {
////                     Log.d("ConsentError", loadAndShowError.message.toString())
////                 }
////
////                   //consent form successfully loaded and displayed
////                   if(consentInformation.canRequestAds()){
////
////                       // Initialize the Google Mobile Ads SDK on a background thread.
////                       MobileAds.initialize(this@MainActivity) {
////                           Log.d("MobileAds", "Mobile Ads SDK initialized.")
////                       }
////
////
////                       //to be safe and prevent probable account ban
////                       MobileAds.setRequestConfiguration(
////                           RequestConfiguration.Builder()
////                               .setTestDeviceIds(listOf("5EEDD4839E298F38292B35ECD2035259",
////                                   "5ED29E53360FA1E3D4AB444DB9B0D7DB"))
////                               .build()
////                       )
////
////                   }
////               }
////
////            },
////            { requestConsentError ->
////                // User's consent status failed to update.
////               Log.d("ConsentError", requestConsentError.message.toString())
////            },
////        )
//
//////////////////////////////////////////////////////////
//
////        // Ensure full-screen layout
////        WindowCompat.setDecorFitsSystemWindows(window, false)
//
//
//    }
//
//
//
//}



class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.S)


    override fun onCreate(savedInstanceState: Bundle?) {
//        val debugSettings = ConsentDebugSettings.Builder(this)
//            .setForceTesting(true) // Forces UMP to show test logs
//            .build()
//
//        val params = ConsentRequestParameters.Builder()
//            .setConsentDebugSettings(debugSettings)
//            .build()
//
//        val consentInfo = UserMessagingPlatform.getConsentInformation(this)
//
//        consentInfo.requestConsentInfoUpdate(
//            this,
//            params,
//            {
//                Log.e("UMP_TEST", "Consent info updated. Check Logcat for test device ID.")
//            },
//            { error ->
//                Log.e("UMP_TEST", "Error: ${error.message}")
//            }
//        )


        super.onCreate(savedInstanceState)





        setContent {
            val navController = rememberNavController()

            Scaffold { padding ->
                AppNavHost(navController, Modifier.padding(padding))
            }

            SideEffect {
                WindowCompat.setDecorFitsSystemWindows(window, false)
            }
        }




        // 🔥 CALL CONSENTMANAGER ONLY ONCE
        ConsentManager.requestConsent(this) {

            MobileAds.initialize(this) {
                Log.d("AdsInit", "Mobile Ads SDK initialized.")
            }

            MobileAds.setRequestConfiguration(
                RequestConfiguration.Builder()
                    .setTestDeviceIds(listOf("5ED29E53360FA1E3D4AB444DB9B0D7DB"))
                    .build()
            )
        }

    }
}

@Composable
fun BannerAd(modifier: Modifier = Modifier) {
//    val adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test Banner
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








