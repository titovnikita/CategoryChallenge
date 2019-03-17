package mykyta.titov.categorychallenge.ui.features.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import mykyta.titov.categorychallenge.R
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
                addFragment(CategoriesFragment.init(), R.id.container, false)
            })

            openHighlightsScreenEvents().observe(this@HomeActivity, Observer { category ->
                addFragment(HighlightsFragment.init(category), R.id.container, false)
            })

            start()
        }
    }
}