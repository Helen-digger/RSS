package rss

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import base.BaseFragment
import com.example.rssreader.R
import injection.PresenterInjector
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.android.synthetic.main.rss_fragment.*
import model.RssItemObject
import javax.inject.Inject

class RssFragment : BaseFragment(), RssContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var presenter: RssContract.Presenter
    private lateinit var adapter: RssItemAdapter

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
    }

    override fun onRssItemsLoaded(rssItems: List<RssItemObject>) {
        adapter.setItems(rssItems)
    }

    override fun onRefresh() {
        presenter.loadRssItems()
    }
}
