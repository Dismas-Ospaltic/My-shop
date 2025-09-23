
package com.diwtech.myshop.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform

fun requestConsent(
    context: Context,
    onReady: (() -> Unit)? = null
) {
    val consentInformation = UserMessagingPlatform.getConsentInformation(context)

    // ✅ Debug settings
    val debugSettings = ConsentDebugSettings.Builder(context)
        // Replace with your hashed test device ID
        .addTestDeviceHashedId("5EEDD4839E298F38292B35ECD2035259")
        // Force geography (EEA / NON_EEA / NOT_EEA)
        .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
        .build()

    val params = ConsentRequestParameters
        .Builder()
        .setConsentDebugSettings(debugSettings) // ✅ Add debug config
        .build()

    consentInformation.requestConsentInfoUpdate(
        context as Activity,
        params,
        {
            // Consent updated
            UserMessagingPlatform.loadAndShowConsentFormIfRequired(context) { formError ->
                if (formError != null) {
                    Log.d("ConsentError", formError.message.toString())
                } else {
                    Log.d("ConsentForm", "Consent form shown successfully")
                }

                if (consentInformation.canRequestAds()) {
                    MobileAds.initialize(context) {
                        Log.d("MobileAds", "Mobile Ads SDK initialized.")
                    }
                }

                onReady?.invoke()
            }
        },
        { requestConsentError ->
            Log.d("ConsentError", requestConsentError.message.toString())
        }
    )
}



//import android.app.Activity
//import android.content.Context
//import android.util.Log
//import com.google.android.gms.ads.MobileAds
//import com.google.android.ump.*
//
//
//
//fun requestConsent(
//    context: Activity,
//    force: Boolean = false, // true = always show consent form
//    onReady: (() -> Unit)? = null
//) {
//    val consentInformation = UserMessagingPlatform.getConsentInformation(context)
//
//    val debugSettings = ConsentDebugSettings.Builder(context)
//        .addTestDeviceHashedId("5EEDD4839E298F38292B35ECD2035259") // Replace with your test ID
//        .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//        .build()
//
//    val params = ConsentRequestParameters.Builder()
//        .setConsentDebugSettings(debugSettings)
//        .build()
//
//    consentInformation.requestConsentInfoUpdate(
//        context,
//        params,
//        {
//            // ✅ Show only if needed OR forced
//            if (consentInformation.isConsentFormAvailable) {
//                if (force || consentInformation.consentStatus == ConsentInformation.ConsentStatus.REQUIRED) {
//                    UserMessagingPlatform.loadConsentForm(
//                        context,
//                        { consentForm ->
//                            consentForm.show(context) { formError ->
//                                if (formError != null) {
//                                    Log.w("ConsentError", formError.message ?: "Unknown error")
//                                } else {
//                                    Log.d("ConsentForm", "Form closed (user made a choice)")
//                                }
//
//                                if (consentInformation.canRequestAds()) {
//                                    initMobileAdsOnce(context)
//                                }
//                                onReady?.invoke()
//                            }
//                        },
//                        { formError ->
//                            Log.w("ConsentError", formError.message ?: "Failed to load form")
//                            onReady?.invoke()
//                        }
//                    )
//                } else {
//                    // ✅ No need to show again
//                    if (consentInformation.canRequestAds()) {
//                        initMobileAdsOnce(context)
//                    }
//                    onReady?.invoke()
//                }
//            } else {
//                // ✅ No form available
//                if (consentInformation.canRequestAds()) {
//                    initMobileAdsOnce(context)
//                }
//                onReady?.invoke()
//            }
//        },
//        { requestConsentError ->
//            Log.w("ConsentError", requestConsentError.message ?: "Request update failed")
//            onReady?.invoke()
//        }
//    )
//}
//
///**
// * Ensure Mobile Ads SDK is initialized only once.
// */
//private var adsInitialized = false
//private fun initMobileAdsOnce(context: Context) {
//    if (!adsInitialized) {
//        MobileAds.initialize(context) {
//            Log.d("MobileAds", "Mobile Ads SDK initialized.")
//        }
//        adsInitialized = true
//    }
//}
//
