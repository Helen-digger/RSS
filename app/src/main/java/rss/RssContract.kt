package rss

import base.BasePresenter
import base.BaseView
import model.RssItemObject
import model.RssObject

interface RssContract {

    // User actions. Presenter will implement
    interface Presenter : BasePresenter<View> {
        fun loadRssItems()
    }

    // Action callbacks. Activity/Fragment will implement
    interface View : BaseView {
        fun onRssItemsLoaded(rssItems: List<RssItemObject>)
    }
}