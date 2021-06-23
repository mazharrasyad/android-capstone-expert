@file:Suppress("unused")

package com.muhazharrasyad.capstoneexpertii

import android.app.Application
import com.muhazharrasyad.capstoneexpertii.core.di.databaseModule
import com.muhazharrasyad.capstoneexpertii.core.di.networkModule
import com.muhazharrasyad.capstoneexpertii.core.di.repositoryModule
import com.muhazharrasyad.capstoneexpertii.di.useCaseModule
import com.muhazharrasyad.capstoneexpertii.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}