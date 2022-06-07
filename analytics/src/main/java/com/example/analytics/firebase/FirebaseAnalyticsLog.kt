package com.example.analytics.firebase

import android.os.Bundle
import com.example.analytics.IAnalyticsLog
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class FirebaseAnalyticsLog : IAnalyticsLog {

    override fun logEvent(name: String, params: Bundle?) {
        Firebase.analytics.logEvent(name, params)
    }
}
