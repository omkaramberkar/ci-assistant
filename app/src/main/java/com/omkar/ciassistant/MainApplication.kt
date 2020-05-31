package com.omkar.ciassistant

import android.os.StrictMode
import com.omkar.ciassistant.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class MainApplication : DaggerApplication() {

    // -----------------------------------------------------------------------------------------
    // Application implementation
    // -----------------------------------------------------------------------------------------

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }

        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    // -----------------------------------------------------------------------------------------
    // Private functions
    // -----------------------------------------------------------------------------------------

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }
}
