package mykyta.titov.categorychallenge.ui.features.image

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_image.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.ui.base.BaseFragment
import mykyta.titov.categorychallenge.utils.extensions.fromUrl

class ImageFragment : BaseFragment<ImageViewModel>() {

    companion object {
        fun init(imageUrl: String) =
                ImageFragment().apply {
                    arguments = Bundle()
                            .apply {
                                putString(EXTRA_IMAGE_URL, imageUrl)
                            }
                }
    }

    override fun getLayoutId(): Int = R.layout.fragment_image

    override fun injectViewModel(): ImageViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ImageViewModel::class.java)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            uiEvents().observe(this@ImageFragment, Observer { uiModel ->
                uiModel?.apply {
                    ivImage.fromUrl(imageUrl)
                }
            })

            start(
                    arguments?.getString(EXTRA_IMAGE_URL)
                            ?: throw IllegalArgumentException("You should provide image url as a param to this fragment!")
            )
        }
    }
}

private const val EXTRA_IMAGE_URL = "image_url"