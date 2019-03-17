package mykyta.titov.categorychallenge.ui.features.categories

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.android.synthetic.main.fragment_categories.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.domain.Category.Weight.*
import mykyta.titov.categorychallenge.ui.base.BaseFragment
import mykyta.titov.categorychallenge.ui.features.items.ItemsFragment
import mykyta.titov.categorychallenge.utils.extensions.longToast
import mykyta.titov.categorychallenge.utils.extensions.replaceFragment


class CategoriesFragment : BaseFragment<CategoriesViewModel>() {

    companion object {
        fun init() = CategoriesFragment()
    }

    override fun getLayoutId(): Int = R.layout.fragment_categories

    override fun injectViewModel(): CategoriesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUiEvents()
        observeNavigationEvents()

        viewModel.start()
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
                                            override fun getSpanSize(position: Int): Int =
                                                    when (state.categories[position].weight) {
                                                        BIG -> 3
                                                        MEDIUM -> 2
                                                        NORMAL -> 1
                                                    }
                                        }
                                    }
                            adapter = CategoriesAdapter(state.categories) { category ->
                                viewModel.onCategoryClicked(category)
                            }
                        }
                    }
                    is State.Error -> {
                        context?.longToast(getString(R.string.error_category_loading))
                    }
                }
            }
        })
    }

    private fun observeNavigationEvents() {
        viewModel.openItemsScreenEvents().observe(this, Observer { category ->
            replaceFragment(ItemsFragment.init(category), R.id.container)
        })
    }
}

private const val GRID_SPAN_COUNT = 3