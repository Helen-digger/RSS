package model

import org.simpleframework.xml.*
import java.io.Serializable

@Root(name = "rss", strict = false)
class RssObject(
    @field:Element(name = "channel")
    var channel:  RSSFeedObject? = null
): Serializable

@Root(name = "channel", strict = false)
class RSSFeedObject(
    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "description")
    var description: String? = null,

    @field:Element(name = "image", required = false)
    var image: RSSImageObject? = null,

    @field:Path("link")
    @field:Text(required=false)
    var link: String? = null,

    @field:ElementList(inline = true, required = false, name = "item")
    var items: MutableList<RssItemObject> = mutableListOf()
): Serializable

@Root(name = "item", strict = false)
data class RssItemObject(
    @field:Element(name = "guid")
    var guid: String,

    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "pubDate")
    var pubDate: String? = null,

    @field:Element(name = "description", required = false)
    var description: String? = null,

    @field:Element(name = "enclosure", required=false)
    var enclosure: RssEnclosureObject? = null

): Serializable  {
    constructor() : this("","","","","",
        RssEnclosureObject("", "", ""))

    fun getUrlImage() : String {
        if (this.description!!.contains("img src=", false)) {
           return this.description!!.substringAfter("<img src=\"").substringBefore("\">")
        } else if (!this.enclosure!!.url.isNullOrEmpty()) {
            return this.enclosure!!.url!!;
        }
        return ""
    }
}

@Root(name = "image", strict = false)
data class RSSImageObject (
    @field:Element(name = "url")
    var imageUrl: String? = null,

    @field:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "title")
    var title: String? = null
): Serializable

@Root(name = "enclosure", strict = false)
data class RssEnclosureObject(
    @field:Attribute(name = "url", required = false)
    var url: String? = null,

    @field:Attribute(name = "type", required = false)
    var type: String? = null,

    @field:Attribute(name = "length", required = false)
    var length: String? = null
): Serializable
