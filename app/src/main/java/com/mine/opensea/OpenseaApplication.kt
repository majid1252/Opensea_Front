package com.mine.opensea

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class OpenseaApplication : MultiDexApplication() {

    companion object {
        lateinit var context: OpenseaApplication private set
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        context = this
        MultiDex.install(this)
    }
}