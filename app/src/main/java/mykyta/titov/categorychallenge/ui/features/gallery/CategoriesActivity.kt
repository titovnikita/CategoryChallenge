package mykyta.titov.categorychallenge.ui.features.gallery

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_categories.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.ui.base.BaseActivity
import mykyta.titov.categorychallenge.ui.features.image.ImageActivity
import mykyta.titov.categorychallenge.utils.extensions.longToast


class CategoriesActivity : BaseActivity<CategoriesViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_categories

    override fun injectViewModel(): CategoriesViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CategoriesViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                            layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT, RecyclerView.VERTICAL, false)
                            adapter = CategoriesAdapter(state.categories) { category ->
                                viewModel.onCategoryClicked(category)
                            }
                        }
                    }
                    is State.Error -> {
                        longToast(getString(R.string.error_category_loading))
                    }
                }
            }
        })
    }

    private fun observeNavigationEvents() {
        viewModel.openImageScreenEvents().observe(this, Observer { imageUrl ->
            ImageActivity.start(this, imageUrl)
        })
    }
}

private const val GRID_SPAN_COUNT = 3