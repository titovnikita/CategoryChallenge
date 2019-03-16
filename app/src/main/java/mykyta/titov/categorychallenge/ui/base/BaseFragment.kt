package mykyta.titov.categorychallenge.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import mykyta.titov.categorychallenge.core.ApplicationBridge
import mykyta.titov.categorychallenge.core.ViewModelFactory

abstract class BaseFragment<out T : BaseViewModel> : Fragment() {

    protected val viewModelFactory: ViewModelFactory by lazy {
        with(context?.applicationContext as ApplicationBridge) { getViewModelFactory() }
    }

    protected val viewModel: T by lazy { injectViewModel() }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(getLayoutId(), container, false)

    protected abstract fun injectViewModel(): T
}