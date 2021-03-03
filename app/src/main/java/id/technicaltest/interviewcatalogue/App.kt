package id.technicaltest.interviewcatalogue

import android.app.Application
import id.technicaltest.core.di.databaseModule
import id.technicaltest.core.di.networkModule
import id.technicaltest.core.di.repositoryModule
import id.technicaltest.interviewcatalogue.dinjection.useCaseModule
import id.technicaltest.interviewcatalogue.dinjection.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
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