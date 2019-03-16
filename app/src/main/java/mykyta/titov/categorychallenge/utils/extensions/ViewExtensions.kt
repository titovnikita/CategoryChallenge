package mykyta.titov.categorychallenge.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ViewGroup.inflate(layoutRes: Int): View =
        LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.fromUrl(url: String, requestOptions: RequestOptions? = null) {
    Glide.with(context)
            .load(url)
            .apply {
                if (requestOptions != null) {
                    apply(requestOptions)
                }

                into(this@fromUrl)
            }
}