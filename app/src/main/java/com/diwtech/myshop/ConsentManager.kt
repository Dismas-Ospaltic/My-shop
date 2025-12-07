package com.diwtech.myshop


import android.app.Activity
import android.util.Log
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform


object ConsentManager {
    private lateinit var consentInformation: ConsentInformation
    private var isFormShowing = false // ✅ Prevent multiple forms at once

    fun requestConsent(activity: Activity, onSuccess: () -> Unit) {
        val debugSettings = ConsentDebugSettings.Builder(activity)
            .addTestDeviceHashedId("5ED29E53360FA1E3D4AB444DB9B0D7DB") // Debug device
            .setDebugGeography(ConsentDebugSettings.DebugGeography.DEBUG_GEOGRAPHY_EEA)
            .build()

        val params = ConsentRequestParameters.Builder()
//            .setConsentDebugSettings(debugSettings)
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

