package injection

import dagger.Module
import dagger.Provides
import rss.RssContract
import rss.RssPresenter
import rss.RssRepository

@Module
class PresenterModule {
    @Provides
    @ActivityScope
    fun provideRssPresenter(repository: RssRepository): RssContract.Presenter = RssPresenter(repository)
}