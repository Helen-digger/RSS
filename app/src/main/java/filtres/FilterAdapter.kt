package filtres

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.rssreader.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import kotlinx.android.synthetic.main.filter_list_item.view.*
import util.FilterTypes
import util.filters.ContrastTransformation
import util.filters.InvertTransformation
import util.filters.SepiaTransformation


class FilterAdapter(private val context: Context)
    : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    private val types = FilterTypes.values()

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
               Picasso.with(context)
                   .load(R.drawable.demo)
                   .transform(BlurTransformation(context, 25, 1))
                   .into(holder.image)
           FilterTypes.Contrast ->
               Picasso.with(context)
                   .load(R.drawable.demo)
                   .transform(ContrastTransformation(context))
                   .into(holder.image)
           FilterTypes.Sepia ->
               Picasso.with(context)
                   .load(R.drawable.demo)
                   .transform(SepiaTransformation(context))
                   .into(holder.image)
           FilterTypes.Invert ->
               Picasso.with(context)
                   .load(R.drawable.demo)
                   .transform(InvertTransformation(context))
                   .into(holder.image)

       }
        holder.title.text = types[position].name

        holder.itemView.setOnClickListener {
            Toast.makeText(context, "!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show()
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

