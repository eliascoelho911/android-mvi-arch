package com.eliascoelho911.androidmvi.sample

import android.app.Application
import com.eliascoelho911.androidmvi.sample.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)

            modules(appModule)
        }
    }
}