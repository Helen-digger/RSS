package filtres

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.example.rssreader.R
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation
import kotlinx.android.synthetic.main.filter_list_item.view.*
import util.FilterTypes


class FilterAdapter(private val context: Context)
    : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private val types = FilterTypes.values()
    private var selectedIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.filter_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       when (types[position]) {
           FilterTypes.Blur ->
               Glide.with(context)
                   .load(R.drawable.demo)
                   .apply(bitmapTransform(FilterTypes.Blur.getTransformationByName()))
                   .into(holder.image)
           FilterTypes.Contrast ->
               Glide.with(context)
                   .load(R.drawable.demo)
                   .apply(bitmapTransform(ContrastFilterTransformation()))
                   .into(holder.image)
           FilterTypes.Sepia ->
               Glide.with(context)
                   .load(R.drawable.demo)
                   .transform(SepiaFilterTransformation())
                   .into(holder.image)
           FilterTypes.Invert ->
               Glide.with(context)
                   .load(R.drawable.demo)
                   .transform(InvertFilterTransformation())
                   .into(holder.image)

       }
        holder.title.text = types[position].name


        holder.itemView.setOnClickListener {
            Toast.makeText(context, types[position].name, Toast.LENGTH_SHORT).show()
            val manager = (holder.itemView.context as FragmentActivity).supportFragmentManager
            manager.setFragmentResult("filter", bundleOf("imageFilter" to types[position].name))
        }
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var title: TextView

        init {
            image = itemView.filter_picture
            title = itemView.filter_name

        }
     }
}

