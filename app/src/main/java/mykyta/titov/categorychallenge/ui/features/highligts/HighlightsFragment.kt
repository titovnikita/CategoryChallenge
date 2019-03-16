package mykyta.titov.categorychallenge.ui.features.highligts

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_highlights.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.ui.base.BaseFragment
import mykyta.titov.categorychallenge.ui.features.categories.CategoriesFragment
import mykyta.titov.categorychallenge.utils.extensions.addFragment
import mykyta.titov.categorychallenge.utils.extensions.fromUrl

class HighlightsFragment : BaseFragment<HighlightsViewModel>() {

    companion object {
        fun init(category: Category) =
                HighlightsFragment().apply {
                    arguments = Bundle()
                            .apply {
                                putParcelable(EXTRA_CATEGORY, category)
                            }
                }
    }

    override fun getLayoutId(): Int = R.layout.fragment_highlights

    override fun injectViewModel(): HighlightsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HighlightsViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            uiEvents().observe(this@HighlightsFragment, Observer { uiModel ->
                uiModel?.apply {
                    ivImage.fromUrl(imageUrl)
                }
            })

            openCategoriesScreenEvents().observe(this@HighlightsFragment, Observer { _ ->
                addFragment(CategoriesFragment.init(), R.id.container)
            })

            btnCategories.setOnClickListener {
                onCategoriesClicked()
            }

            start(
                    arguments?.getParcelable(EXTRA_CATEGORY)
                            ?: throw IllegalArgumentException("You should provide category as a param to this fragment!")
            )
        }
    }
}

private const val EXTRA_CATEGORY = "key_category"