package filtres

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.rssreader.R
import injection.PresenterInjector
import kotlinx.android.synthetic.main.filter_fragment.*
import kotlinx.android.synthetic.main.rss_fragment.*
import rss.RssContract
import rss.RssItemAdapter
import javax.inject.Inject

class FiltersFragment : BaseFragment(), FilterContract.View {

    private lateinit var adapter: FilterAdapter
    override val layoutResource = R.layout.filter_fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FilterAdapter(requireActivity())
        filter_list.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        filter_list.adapter = adapter
    }


    override fun inject(component: PresenterInjector) {
        component.inject(this)
    }
}