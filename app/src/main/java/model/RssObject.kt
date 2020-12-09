package model

import androidx.room.Entity
import androidx.room.PrimaryKey
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

    /*var imageUrl: String? = null,

    var channelKey: String? = null,

    var isRead: Int*/

): Serializable  {
    constructor() : this("","","","","", enclosure = null)
}/*{

    constructor()

    companion object {
        val READ = 1
    }

    fun setChannelUrl(channelUrl: String?) {
        channelKey = channelUrl
    }

    fun setImageLink(imageUrl: String?) {
        this.imageUrl = imageUrl
    }

    fun getDescriptionImage(description: String?) : String {
        return if (description!!.contains("img src=", false)) {
           description.substringAfter("<img src=\"").substringBefore("\">")
        } else {
            ""
        }
    }
}*/

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
    @field:Element(name = "url")
    var url: String? = null,

    @field:Element(name = "type")
    var type: String? = null,

    @field:Element(name = "length")
    var length: String? = null
): Serializable {
    override fun toString(): String {
        return "$url, $type, $length"
    }
}
