package mykyta.titov.categorychallenge.ui.features.categories

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_category.view.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.utils.extensions.fromUrl
import mykyta.titov.categorychallenge.utils.extensions.inflate

class CategoriesAdapter(
        private val data: List<Category>,
        private val onItemClickListener: (Category) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            CategoryViewHolder(parent.inflate(R.layout.item_category), onItemClickListener)

    class CategoryViewHolder(
            itemView: View,
            private val onItemClickListener: (Category) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(category: Category) {
            with(itemView) {
                ivImage.fromUrl(category.imageUrl, RequestOptions.centerCropTransform())

                setOnClickListener { onItemClickListener(category) }
            }
        }
    }
}