package rss

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rssreader.R
import com.example.rssreader.databinding.RssItemLayoutBinding
import detail.ItemReadActivity
import kotlinx.android.synthetic.main.rss_item_layout.view.*
import model.RssItemObject
import util.FilterTypes
import java.util.ArrayList

class RssItemAdapter(private val context: Context) : RecyclerView.Adapter<RssItemAdapter.ViewHolder>() {

    private val items = ArrayList<RssItemObject>()
    private var filter : FilterTypes? = null

    fun setItems(rssItems: List<RssItemObject>) {
        items.clear()
        items.addAll(rssItems)
        notifyDataSetChanged()
    }

    fun setFilter(filter : FilterTypes) {
        this.filter = filter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: RssItemLayoutBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.rss_item_layout, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (items.size <= position) {
            return
        }

        holder.bind(items[position])
        if (filter != null) {
            Glide.with(context).load(R.drawable.demo).transform(filter?.getTransformationByName())
                .into(holder.imageThumb)
        }
    }

    class ViewHolder(private val binding: RssItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        var imageThumb = itemView.news_img!!
        var source_title = itemView.source_news_name!!

        fun bind(post: RssItemObject) {
            binding.post = post
            binding.executePendingBindings()

            binding.root.setOnClickListener { view ->
                val detailIntent = Intent(view.context, ItemReadActivity::class.java)
                detailIntent.putExtra("post", post)
                view.context.startActivity(detailIntent)
            }
        }
    }
}