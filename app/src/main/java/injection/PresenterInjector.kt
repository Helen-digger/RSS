package injection

import dagger.Component
import filtres.FiltersFragment
import main.MainActivity
import rss.RssFragment

@ActivityScope
@Component(dependencies = [ApplicationInjector::class], modules = [PresenterModule::class])
interface PresenterInjector {

    fun inject(mainActivity: MainActivity)

    fun inject(rssFragment: RssFragment)

    fun inject(filtersFragment: FiltersFragment)

}