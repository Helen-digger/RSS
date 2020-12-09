package main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import base.BaseActivity
import com.example.rssreader.R
import filtres.FiltersFragment
import injection.PresenterInjector
import rss.RssFragment

class MainActivity : BaseActivity() {

    override fun inject(component: PresenterInjector) {
        component.inject(this)
    }

    override val layoutResource = R.layout.main_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<RssFragment>(R.id.rss_fragment)
                add<FiltersFragment>(R.id.filter_fragment)
            }
        }
    }

    override fun init(state: Bundle?) {

    }
}
