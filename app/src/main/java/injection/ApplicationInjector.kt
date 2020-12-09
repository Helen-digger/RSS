package injection

import dagger.Component
import network.RssService
import rss.RssRepository
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class
])
interface ApplicationInjector {
    fun service(): RssService

    fun rssRepository(): RssRepository
}