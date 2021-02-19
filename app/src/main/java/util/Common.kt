package common

import com.google.gson.Gson
import java.io.File
import java.util.*

object Common {

    val BASE_URL = "https://habr.com/ru/rss/hubs/all/"//"https://habr.com/ru/rss/hubs/all/"

    /*companion object {
        private const val RSS_FILE = "rss.txt"
    }

    fun parseDefaultChannelsName(): List<String> {
        val gson = Gson()
        val text = File(RSS_FILE).readText()
        val names = gson.fromJson(text, Array<String>::class.java)
        return listOf(*names)
    }*/


}