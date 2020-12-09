package rss

import model.RssObject

interface RssResponse {
    fun getRssResponse(url: String, rssObject: RssObject)
}