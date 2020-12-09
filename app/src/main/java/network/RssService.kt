package network

import model.RssObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {
    @GET()
    fun getRssObject(@Url url: String): Call<RssObject>
}
