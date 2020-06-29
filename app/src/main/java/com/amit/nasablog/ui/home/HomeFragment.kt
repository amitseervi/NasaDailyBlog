package com.amit.nasablog.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.amit.nasablog.BR
import com.amit.nasablog.R
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.ui.NasaBlogAppViewModelProvider
import com.amit.nasablog.ui.base.BaseFragment
import com.amit.nasablog.ui.home.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

class HomeFragment : BaseFragment<ViewDataBinding>(R.layout.fragment_home) {

    @Inject
    lateinit var viewModelProvider: NasaBlogAppViewModelProvider

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider.create(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.setSupportActionBar(view.toolbar)
        viewModel.getBlogDetail().observe(viewLifecycleOwner, Observer {
            setBlogDetail(it)
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer {
            setError(it)
        })
        getBinding()?.let { binding ->
            binding.setVariable(BR.viewModel, viewModel)
            //NOTE : always set life cycle after setting all other variable
            binding.lifecycleOwner = viewLifecycleOwner
        }
        viewModel.loadBlog()
    }

    private fun setBlogDetail(blogDetail: BlogDetail?) {
        val imgUrl = blogDetail?.getThumbnail()
        if (imgUrl.isNullOrBlank()) {
            view?.iv_img?.visibility = View.GONE
        }
        view?.let { view ->
            view.iv_img.visibility = View.VISIBLE
            val requestOptions = RequestOptions().fitCenter()
                .transform(RoundedCorners(view.context.resources.getDimensionPixelSize(R.dimen.img_corner_radius)))
            Glide.with(view.iv_img).applyDefaultRequestOptions(requestOptions)
                .load(imgUrl).into(view.iv_img)
        }
    }

    private fun setError(error: Throwable?) {

    }

}