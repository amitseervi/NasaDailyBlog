package com.amit.nasablog.ui.preview

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.amit.nasablog.R
import com.amit.nasablog.databinding.FragmentImagePreviewBinding
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.ui.base.BaseFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.fragment_image_preview.view.*

class ImagePreviewFragment :
    BaseFragment<FragmentImagePreviewBinding>(R.layout.fragment_image_preview) {
    private var blogDetail: BlogDetail? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        blogDetail = arguments?.getSerializable(BUNDLE_ARG_BLOG_DETAIL) as BlogDetail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.content_progress.show()
        blogDetail?.let { detail ->
            view.touch_img?.let { touchView ->
                Glide.with(touchView).load(detail.hdurl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            Toast.makeText(context, "Image loading failed", Toast.LENGTH_LONG)
                                .show()
                            view.content_progress.hide()
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            view.content_progress.hide()
                            return false
                        }

                    }).into(touchView)
            }
        }
    }

    companion object {
        const val BUNDLE_ARG_BLOG_DETAIL = "blog_detail"
    }

}