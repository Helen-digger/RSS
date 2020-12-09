package util

import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import detail.NewsWebViewClient
import rss.RssItemAdapter

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RssItemAdapter) {
    view.adapter = adapter
}

@BindingAdapter("layoutManager")
fun setLayoutManager(view: RecyclerView, layoutManager: RecyclerView.LayoutManager) {
    view.layoutManager = layoutManager
}

@BindingAdapter("dividerItemDecoration")
fun setDividerItemDecoration(view: RecyclerView, dividerItemDecoration: DividerItemDecoration) {
    view.addItemDecoration(dividerItemDecoration)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
@BindingAdapter("bind_url")
fun bindUrl(view : WebView, url : String) {
    view.settings.apply {
        javaScriptEnabled = true
        javaScriptCanOpenWindowsAutomatically = false
        loadWithOverviewMode = true
        useWideViewPort = true
        builtInZoomControls = false
        layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        cacheMode = WebSettings.LOAD_NO_CACHE
        domStorageEnabled = true
        setSupportZoom(false)
        setSupportMultipleWindows(true)
    }
    view.webViewClient = NewsWebViewClient()
    view.loadUrl(url)
}