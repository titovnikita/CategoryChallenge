package mykyta.titov.categorychallenge.ui.features.items

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.android.synthetic.main.fragment_categories.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.ui.base.BaseFragment
import mykyta.titov.categorychallenge.ui.features.image.ImageFragment
import mykyta.titov.categorychallenge.utils.extensions.addFragment
import mykyta.titov.categorychallenge.utils.extensions.longToast


class ItemsFragment : BaseFragment<ItemsViewModel>() {

    companion object {
        fun init(category: Category) = ItemsFragment()
                .apply {
                    arguments = Bundle()
                            .apply {
                                putParcelable(EXTRA_CATEGORY, category)
                            }
                }
    }

    override fun getLayoutId(): Int = R.layout.fragment_items

    override fun injectViewModel(): ItemsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ItemsViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiEvents()
        observeNavigationEvents()

        viewModel.start(
                arguments?.getParcelable(EXTRA_CATEGORY)
                        ?: throw IllegalArgumentException("You should provide category as a param to this fragment!")
        )
    }

    private fun observeUiEvents() {
        viewModel.uiEvents().observe(this, Observer { uiModel ->
            uiModel?.apply {
                when (state) {
                    is State.Loaded -> {
                        with(rvImages) {
                            layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT, VERTICAL, false)
                                    .apply {
                                        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                                            override fun getSpanSize(position: Int): Int {
                                                return 1
                                            }
                                        }
                                    }
                            adapter = ItemsAdapter(state.items) { item ->
                                viewModel.onItemClicked(item)
                            }
                        }
                    }
                    is State.Error -> {
                        context?.longToast(getString(R.string.error_item_loading))
                    }
                }
            }
        })
    }

    private fun observeNavigationEvents() {
        viewModel.openImageScreenEvents().observe(this, Observer { imageUrl ->
            addFragment(ImageFragment.init(imageUrl), R.id.container)
        })
    }
}

private const val GRID_SPAN_COUNT = 3
private const val EXTRA_CATEGORY = "key_category"