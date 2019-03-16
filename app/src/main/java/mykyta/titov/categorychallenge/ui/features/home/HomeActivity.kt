package mykyta.titov.categorychallenge.ui.features.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.domain.Category
import mykyta.titov.categorychallenge.ui.base.BaseActivity
import mykyta.titov.categorychallenge.ui.features.categories.CategoriesFragment
import mykyta.titov.categorychallenge.ui.features.highligts.HighlightsFragment
import mykyta.titov.categorychallenge.utils.extensions.addFragment

class HomeActivity : BaseActivity<HomeViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun injectViewModel(): HomeViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel) {
            openCategoriesScreenEvents().observe(this@HomeActivity, Observer { _ ->
                addFragment(CategoriesFragment.init(), R.id.container)
            })

            openHighlightsScreenEvents().observe(this@HomeActivity, Observer { _ ->
                addFragment(HighlightsFragment.init(Category("123", "https://images.unsplash.com/photo-1551999158-75d41e4860a5?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max")), R.id.container)
            })

            start()
        }
    }
}