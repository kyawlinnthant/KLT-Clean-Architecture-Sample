package com.example.klt_clean_architecture_sample

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.bumptech.glide.annotation.GlideModule
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@GlideModule
@HiltAndroidApp
class MovieApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant()
    }
}