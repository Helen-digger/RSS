package rss

import base.BasePresenterImpl
import common.Common
import model.RssObject
import javax.inject.Inject

class RssPresenter @Inject
constructor(private val repository: RssRepository) :
    BasePresenterImpl<RssContract.View>(), RssContract.Presenter, RssResponse {

    override fun loadRssItems() {
        repository.fetchRss("https://habr.com/ru/rss/hubs/all/", this)
    }

    override fun getRssResponse(url: String, rssObject: RssObject) {
        view!!.onRssItemsLoaded(rssObject.channel!!.items)
        rssObject.channel!!.image?.imageUrl?.let { view!!.onChannelImage(it) }
    }

}
