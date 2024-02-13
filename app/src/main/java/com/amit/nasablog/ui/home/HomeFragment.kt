package com.amit.nasablog.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.amit.nasablog.R
import com.amit.nasablog.databinding.FragmentHomeBinding
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.ui.base.BaseFragment
import com.amit.nasablog.ui.error.ErrorDialogFragment
import com.amit.nasablog.ui.home.viewmodel.HomeViewModel
import com.amit.nasablog.ui.preview.ImagePreviewFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()


    private fun bindViewModel() {
        lifecycleScope.launch {
            viewModel.blogDetail.collectLatest {
                setBlogDetail(it)
            }
        }

        lifecycleScope.launch {
            viewModel.errorResponse.collectLatest {
                setError(it)
            }
        }

        lifecycleScope.launch {
            viewModel.showProgress.collectLatest {
                showProgress(it)
            }
        }
    }

    private fun bindMenu() {
        binding?.toolbar?.inflateMenu(R.menu.menu_home)
        binding?.toolbar?.setOnMenuItemClickListener {
            if (it.itemId == R.id.calendar_date_select) {
                showDatePicker()
                true
            } else {
                false
            }
        }
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            binding?.contentProgress?.show()
        } else {
            binding?.contentProgress?.hide()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
        bindMenu()

        viewModel.loadBlog()
        binding?.let { binding ->
            binding.viewModel = viewModel
            binding.fab.setOnClickListener {
                val blogDetail = viewModel.blogDetail.value ?: return@setOnClickListener
                val args = Bundle()
                args.putSerializable(ImagePreviewFragment.BUNDLE_ARG_BLOG_DETAIL, blogDetail)
                if (blogDetail.isVideoSource()) {
                    val url: String? = blogDetail.url
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                } else {
                    findNavController().navigate(
                        R.id.action_homeFragment_to_imagePreviewFragment,
                        args
                    )
                }
            }
            //NOTE : always set life cycle after setting all other variable
            binding.lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun setBlogDetail(blogDetail: BlogDetail?) {
        val imgUrl = blogDetail?.getThumbnail()
        if (imgUrl.isNullOrBlank()) {
            binding?.ivImg?.visibility = View.GONE
        }
        binding?.let { binding ->
            binding.ivImg.visibility = View.VISIBLE
            val requestOptions = RequestOptions().fitCenter()
                .transform(RoundedCorners(requireContext().resources.getDimensionPixelSize(R.dimen.img_corner_radius)))
            Glide.with(binding.ivImg).applyDefaultRequestOptions(requestOptions)
                .load(imgUrl).into(binding.ivImg)

            blogDetail?.let { blog ->
                if (blog.isVideoSource()) {
                    binding.fab.show()
                    binding.fab.setImageResource(R.drawable.ic_play_button)
                } else {
                    if (blog.url.isNullOrBlank()) {
                        binding.fab.hide()
                    } else {
                        binding.fab.show()
                        binding.fab.setImageResource(R.drawable.ic_fullscreen)
                    }
                }
            }
        }
    }

    private fun setError(error: Throwable?) {
        if (error != null) {
            val errorFragment = ErrorDialogFragment()
            val args = Bundle()
            args.putString(ErrorDialogFragment.MESSAGE, error.message)
            errorFragment.arguments = args
            errorFragment.show(childFragmentManager, "error_message")
        }
    }

    private fun showDatePicker() {
        val builder = MaterialDatePicker.Builder.datePicker()
        if (viewModel.currentSelectedDate > 0) {
            builder.setSelection(viewModel.currentSelectedDate)
        }
        val picker = builder.build()
        picker.addOnPositiveButtonClickListener {
            viewModel.onDateClicked(it)
        }
        picker.show(childFragmentManager, "date_picker")
    }

}