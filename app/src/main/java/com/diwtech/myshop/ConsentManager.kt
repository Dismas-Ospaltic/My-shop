package com.diwtech.myshop
//
//
//import android.app.Activity
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import androidx.compose.material3.*
//import com.google.android.ump.ConsentDebugSettings
//import com.google.android.ump.ConsentInformation
//import com.google.android.ump.ConsentRequestParameters
//import com.google.android.ump.UserMessagingPlatform
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//
////Keep MainActivity clean. Put consent logic in a ConsentManager object/class:
//object ConsentManager {
//    private lateinit var consentInformation: ConsentInformation
//
//    fun requestConsent(activity: Activity, onSuccess: () -> Unit) {
//        val debugSettings = ConsentDebugSettings.Builder(activity)
//            .addTestDeviceHashedId("5EEDD4839E298F38292B35ECD2035259")
//            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//            .build()
//
//        val params = ConsentRequestParameters.Builder()
//            .setConsentDebugSettings(debugSettings)
//            .build()
//
//        consentInformation = UserMessagingPlatform.getConsentInformation(activity)
//
//        consentInformation.requestConsentInfoUpdate(
//            activity,
//            params,
//            {
//                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
//                    formError?.let { Log.d("ConsentError", it.message.toString()) }
//
//                    if (consentInformation.canRequestAds()) {
//                        onSuccess()
//                    }
//                }
//            },
//            { error -> Log.d("ConsentError", error.message.toString()) }
//        )
//    }
//
//    fun canRequestAds(): Boolean = this::consentInformation.isInitialized &&
//            consentInformation.canRequestAds()
//}


import android.app.Activity
import android.util.Log
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform

//object ConsentManager {
//    private lateinit var consentInformation: ConsentInformation
//
//    fun requestConsent(activity: Activity, onSuccess: () -> Unit) {
//        val debugSettings = ConsentDebugSettings.Builder(activity)
//            .addTestDeviceHashedId("5EEDD4839E298F38292B35ECD2035259") // Debug device
//            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
//            .build()
//
//        val params = ConsentRequestParameters.Builder()
//            .setConsentDebugSettings(debugSettings)
//            .build()
//
//        consentInformation = UserMessagingPlatform.getConsentInformation(activity)
//
//        consentInformation.requestConsentInfoUpdate(
//            activity,
//            params,
//            {
//                UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
//                    formError?.let { Log.d("ConsentError", it.message.toString()) }
//
//                    if (consentInformation.canRequestAds()) {
//                        onSuccess()
//                    }
//                }
//            },
//            { error -> Log.d("ConsentError", error.message.toString()) }
//        )
//    }
//
////    fun openConsentForm(activity: Activity) {
////        UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
////            formError?.let { Log.d("ConsentError", it.message.toString()) }
////        }
////    }
//
//    // ✅ NEW: Function to show the privacy options form for editing consent
////    fun showPrivacyOptionsForm(activity: Activity) {
////        // This function will always show the form, allowing users to
////        // update their previously given consent.
//////        UserMessagingPlatform.showPrivacyOptionsForm(activity) { formError ->
////            UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
////            formError?.let {
////                Log.w("ConsentManager", "Error showing privacy options form: ${it.message}")
////            }
////        }
////    }
//
//
//    fun showPrivacyOptionsForm(activity: Activity, onDismissed: (() -> Unit)? = null) {
//        UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
//            formError?.let {
//                Log.e("ConsentManager", "Consent form error: ${it.message}")
//            }
//            // Always call onDismissed when the form closes (success or error)
//            onDismissed?.invoke()
//        }
//    }
//
//    fun canRequestAds(): Boolean =
//        this::consentInformation.isInitialized && consentInformation.canRequestAds()
//}

object ConsentManager {
    private lateinit var consentInformation: ConsentInformation
    private var isFormShowing = false // ✅ Prevent multiple forms at once

    fun requestConsent(activity: Activity, onSuccess: () -> Unit) {
        val debugSettings = ConsentDebugSettings.Builder(activity)
            .addTestDeviceHashedId("5EEDD4839E298F38292B35ECD2035259") // Debug device
//            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
            .build()

        val params = ConsentRequestParameters.Builder()
            .setConsentDebugSettings(debugSettings)
            .build()

        consentInformation = UserMessagingPlatform.getConsentInformation(activity)

        consentInformation.requestConsentInfoUpdate(
            activity,
            params,
            {
                // ✅ Only show if available and not already showing
                if (consentInformation.isConsentFormAvailable && !isFormShowing) {
                    isFormShowing = true
                    UserMessagingPlatform.loadAndShowConsentFormIfRequired(activity) { formError ->
                        isFormShowing = false
                        formError?.let {
                            Log.e("ConsentManager", "Consent form error: ${it.message}")
                        }

                        if (consentInformation.canRequestAds()) {
                            onSuccess()
                        }
                    }
                } else {
                    // No form required
                    if (consentInformation.canRequestAds()) {
                        onSuccess()
                    }
                }
            },
            { error ->
                Log.e("ConsentManager", "Consent info update error: ${error.message}")
            }
        )
    }

    // ✅ For "Manage Ad Preferences" in Settings
    fun showPrivacyOptionsForm(activity: Activity, onDismissed: (() -> Unit)? = null) {
        if (isFormShowing) return // don’t show twice

        isFormShowing = true
        UserMessagingPlatform.showPrivacyOptionsForm(activity) { formError ->
            isFormShowing = false
            formError?.let {
                Log.e("ConsentManager", "Privacy options error: ${it.message}")
            }
            onDismissed?.invoke()
        }
    }


    //to determine whethher to sho ad button or not
    fun isPrivacyOptionsRequired(): Boolean {
        return this::consentInformation.isInitialized &&
                consentInformation.privacyOptionsRequirementStatus ==
                ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED
    }


    fun canRequestAds(): Boolean =
        this::consentInformation.isInitialized && consentInformation.canRequestAds()
}

