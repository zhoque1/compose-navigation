package com.example.mycomposeapplication

import android.app.Application
import com.example.mycomposeapplication.data.di.apiModule
import com.example.mycomposeapplication.data.di.repositoryModule
import com.example.mycomposeapplication.data.di.useCaseModule
import com.example.mycomposeapplication.di.viewModelModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(
                apiModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}