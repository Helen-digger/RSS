package injection

import com.facebook.stetho.okhttp3.StethoInterceptor
import common.Common
import dagger.Module
import dagger.Provides
import dagger.Reusable
import network.RssService
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import rss.RssRepository
import rss.RssRepositoryImpl
import java.io.File
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    private val cacheSize: Long = 10 * 1024 * 1024
    private val cacheTimeSec = 30

    private val cacheInterceptor: Interceptor
        get() = Interceptor {
            val response = it.proceed(it.request())
            val cacheControl = CacheControl.Builder()
                    .maxAge(cacheTimeSec, TimeUnit.SECONDS)
                    .build()

            response.newBuilder()
                    .header("Cache-Control", cacheControl.toString())
                    .build()
        }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideOkHttpClient(): OkHttpClient {
        val cache = Cache(File("okhttp.cache"), cacheSize)
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .cache(cache)
                .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun getService(retrofit: Retrofit): RssService {
        return retrofit.create(RssService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Common.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            //.addConverterFactory(MoshiConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRssRepository(service: RssService): RssRepository = RssRepositoryImpl(service)
}
