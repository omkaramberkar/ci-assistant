package com.omkar.core.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import com.omkar.core.BuildConfig
import timber.log.Timber

const val SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService"
const val CHROME_PACKAGE = "com.android.chrome"

/**
 * Helper to force a when statement to assert all options are matched in a when statement.
 *
 * By default, Kotlin doesn't care if all branches are handled in a when statement. However, if you
 * use the when statement as an expression (with a value) it will force all cases to be handled.
 *
 * This helper is to make a lightweight way to say you meant to match all of them.
 *
 * Usage:
 *
 * ```
 * when(sealedObject) {
 *     is OneType -> //
 *     is AnotherType -> //
 * }.checkAllMatched
 */
val <T> T.checkAllMatched: T
    get() = this

/**
 * Helper to throw exceptions only in Debug builds, logging a warning otherwise.
 */
fun exceptionInDebug(t: Throwable) {
    if (BuildConfig.DEBUG) {
        throw t
    } else {
        Timber.e(t)
    }
}

fun Context.launchUrl(url: String?) {
    url ?: return
    if (isChromeCustomTabsSupported()) {
        CustomTabsIntent.Builder().apply {

        }.build().launchUrl(this, Uri.parse(url))
    } else {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }
        startActivity(intent, null)
    }
}

fun Context.isChromeCustomTabsSupported(): Boolean {
    val serviceIntent = Intent(SERVICE_ACTION)
    serviceIntent.setPackage(CHROME_PACKAGE)
    val resolveInfos = packageManager.queryIntentServices(serviceIntent, 0)
    return !(resolveInfos == null || resolveInfos.isEmpty())
}
