package com.keshav.newsapp

import android.app.Application
import com.keshav.newsapp.di.newsAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApplication)
            modules(listOf(newsAppModule))
        }
    }
}