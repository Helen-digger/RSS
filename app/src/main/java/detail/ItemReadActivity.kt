package detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rssreader.R
import com.example.rssreader.databinding.ActivityItemReadBinding
import model.RssItemObject

class ItemReadActivity  : AppCompatActivity() {

    private lateinit var mBinding : ActivityItemReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_read)
        mBinding.apply {
            val extras = intent.extras
            items = (if (extras != null) extras.get("post") else null) as RssItemObject
        }
    }
}