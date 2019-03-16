package mykyta.titov.categorychallenge.ui.features.image

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_image.*
import mykyta.titov.categorychallenge.R
import mykyta.titov.categorychallenge.ui.base.BaseActivity
import mykyta.titov.categorychallenge.utils.extensions.fromUrl

class ImageActivity : BaseActivity<ImageViewModel>() {

    companion object {
        fun start(context: Context, imageUrl: String) {
            with(context) {
                startActivity(
                        Intent(this, ImageActivity::class.java)
                                .apply {
                                    putExtra(EXTRA_IMAGE_URL, imageUrl)
                                }
                )
            }
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_image

    override fun injectViewModel(): ImageViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(ImageViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.uiEvents().observe(this, Observer { uiModel ->
            uiModel?.apply {
                ivImage.fromUrl(imageUrl)
            }
        })

        viewModel.start(
                intent.getStringExtra(EXTRA_IMAGE_URL)
                        ?: throw IllegalArgumentException("You should provide image url as a param to this activity!")
        )
    }
}

private const val EXTRA_IMAGE_URL = "image_url"