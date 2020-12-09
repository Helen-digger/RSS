package rss

import android.util.Log
import model.RssObject
import network.RssService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RssRepositoryImpl @Inject constructor(private val service: RssService) : RssRepository {
    override fun fetchRss(url: String, rssResponse: RssResponse) {
        service.getRssObject(url).enqueue(object : Callback<RssObject> {
            override fun onFailure(call: Call<RssObject>, t: Throwable) {
                Log.e("NETWORK", t.message!!)
            }

            override fun onResponse(call: Call<RssObject>, response: Response<RssObject>) {
                response.body()?.let {
                    Log.e("NETWORK", it.toString())
                    rssResponse.getRssResponse(url, it) }
            }
        })
    }
}

interface RssRepository {
    fun fetchRss(url: String, rssResponse: RssResponse)
}