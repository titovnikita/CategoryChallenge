package mykyta.titov.categorychallenge.ui.features.gallery

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_category.view.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.utils.extensions.fromUrl
import mykyta.titov.categorychallenge.utils.extensions.inflate

class CategoriesAdapter(
        private val data: List<Category>,
        private val onItemClickListener: (Category) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.FeedItemViewHolder>() {

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            FeedItemViewHolder(parent.inflate(R.layout.item_category), onItemClickListener)

    class FeedItemViewHolder(
            itemView: View,
            private val onItemClickListener: (Category) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            with(itemView) {
                ivImage.fromUrl(category.cover.regular)

                setOnClickListener { onItemClickListener(category) }
            }
        }
    }
}