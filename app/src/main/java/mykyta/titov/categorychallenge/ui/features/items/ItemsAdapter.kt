package mykyta.titov.categorychallenge.ui.features.items

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_category.view.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.domain.Item
import mykyta.titov.categorychallenge.utils.extensions.fromUrl
import mykyta.titov.categorychallenge.utils.extensions.inflate

class ItemsAdapter(
        private val data: List<Item>,
        private val onItemClickListener: (Item) -> Unit
) : RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(parent.inflate(R.layout.item_category), onItemClickListener)

    class ItemViewHolder(
            itemView: View,
            private val onItemClickListener: (Item) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            with(itemView) {
                ivImage.fromUrl(item.imageUrl, RequestOptions.centerCropTransform())

                setOnClickListener { onItemClickListener(item) }
            }
        }
    }
}