package mykyta.titov.categorychallenge.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import mykyta.titov.categorychallenge.core.ViewModelFactory
import mykyta.titov.categorychallenge.core.ApplicationBridge

abstract class BaseActivity<out T : BaseViewModel> : AppCompatActivity() {

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected val viewModelFactory: ViewModelFactory by lazy {
        with(applicationContext as ApplicationBridge) { getViewModelFactory() }
    }

    protected val viewModel: T by lazy { injectViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    protected abstract fun injectViewModel(): T
}