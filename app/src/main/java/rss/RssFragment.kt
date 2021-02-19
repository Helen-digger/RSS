package rss

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import base.BaseFragment
import com.example.rssreader.R
import injection.PresenterInjector
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.android.synthetic.main.rss_fragment.*
import model.RssItemObject
import util.FilterTypes
import javax.inject.Inject

class RssFragment : BaseFragment(), RssContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: RssContract.Presenter
    private lateinit var adapter: RssItemAdapter
    var filter : String? = null

    override val layoutResource = R.layout.rss_fragment

    override fun inject(component: PresenterInjector) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        adapter = RssItemAdapter(requireActivity())
        recycler_view_source_name.layoutManager = LinearLayoutManager(activity)
        recycler_view_source_name.adapter = adapter
        scroll_view.setOnRefreshListener(this)
        presenter.loadRssItems()

        val defaultFilter = PreferenceManager.getDefaultSharedPreferences(activity)
            .getString("filter", null).let {
                if (it != null) {
                    adapter.setFilter(FilterTypes.getByName(it))
                    adapter.notifyDataSetChanged();
                }
            }

        setFragmentResultListener("filter") {
            requestKey, bundle ->
            filter = bundle.getString("imageFilter")
            filter?.let { adapter.setFilter(FilterTypes.getByName(filter!!))
                adapter.notifyDataSetChanged();
            }
        }
    }

    override fun onRssItemsLoaded(rssItems: List<RssItemObject>) {
        adapter.setItems(rssItems)
    }

    override fun onChannelImage(url: String) {
        adapter.setChannelLogo(url)
    }

    override fun onRefresh() {
        presenter.loadRssItems()
    }
}
