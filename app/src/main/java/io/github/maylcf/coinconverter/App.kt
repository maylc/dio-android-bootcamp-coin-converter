package io.github.maylcf.coinconverter

import android.app.Application
import io.github.maylcf.coinconverter.data.di.DataModules
import io.github.maylcf.coinconverter.domain.di.DomainModule
import io.github.maylcf.coinconverter.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@App) }
        DataModules.load()
        DomainModule.load()
        PresentationModule.load()
    }
}