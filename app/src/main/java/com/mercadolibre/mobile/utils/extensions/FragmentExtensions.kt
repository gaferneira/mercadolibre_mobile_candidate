package com.mercadolibre.mobile.utils.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.mercadolibre.mobile.R
import com.mercadolibre.mobile.utils.view.GlideApp

fun Fragment.setSharedElementTransitionOnEnter() {
    sharedElementEnterTransition = TransitionInflater.from(context)
        .inflateTransition(R.transition.shared_element_transition)
}

fun Fragment.setExitToFullScreenTransition() {
    exitTransition =
        TransitionInflater.from(context).inflateTransition(R.transition.list_exit_transition)
}

fun Fragment.setReturnFromFullScreenTransition() {
    reenterTransition =
        TransitionInflater.from(context).inflateTransition(R.transition.list_return_transition)
}

fun Fragment.navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) = with(findNavController()) {
    currentDestination?.getAction(destination.actionId)
        ?.let {
            navigate(destination, extraInfo)
        }
}

fun Fragment.startEnterTransitionAfterLoadingImage(
    imageAddress: String,
    imageView: ImageView
) {
    GlideApp.with(this)
        .load(imageAddress)
        .apply(
            RequestOptions().dontTransform()
        )
        .dontAnimate()
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                startPostponedEnterTransition()
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: com.bumptech.glide.request.target.Target<Drawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                startPostponedEnterTransition()
                return false
            }
        })
        .into(imageView)
}
