package app

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import injection.ApplicationInjector
import injection.ApplicationModule
import injection.DaggerApplicationInjector

class App : Application() {

    companion object {
        lateinit var instance: Application

        private var appComponent: ApplicationInjector? = null

        fun component(): ApplicationInjector {
            return appComponent!!
        }

        fun setComponent(component: ApplicationInjector) {
            appComponent = component
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        LeakCanary.install(this)
        setComponent(
            DaggerApplicationInjector.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        )
    }
}