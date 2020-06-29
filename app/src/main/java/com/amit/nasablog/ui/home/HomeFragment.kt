package com.amit.nasablog.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.amit.nasablog.R
import com.amit.nasablog.databinding.FragmentHomeBinding
import com.amit.nasablog.model.entity.BlogDetail
import com.amit.nasablog.ui.NasaBlogAppViewModelProvider
import com.amit.nasablog.ui.base.BaseFragment
import com.amit.nasablog.ui.home.viewmodel.HomeViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.fragment_home.view.*
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

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
        setHasOptionsMenu(true)
        viewModel.getBlogDetail().observe(viewLifecycleOwner, Observer {
            setBlogDetail(it)
        })

        viewModel.getError().observe(viewLifecycleOwner, Observer {
            setError(it)
        })

        viewModel.showProgress().observe(viewLifecycleOwner, Observer {
            Timber.tag("amittest").d("Show progress ${it}")
            if (it) {
                view.content_progress?.show()
            } else {
                view.content_progress?.hide()
            }
        })
        getBinding()?.let { binding ->
            binding.viewModel = viewModel
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

            blogDetail?.let { blog ->
                if (blog.isVideoSource()) {
                    view.fab.show()
                    view.fab.setImageResource(R.drawable.ic_play_button)
                } else {
                    if (blog.url.isNullOrBlank()) {
                        view.fab.hide()
                    } else {
                        view.fab.show()
                        view.fab.setImageResource(R.drawable.ic_fullscreen)
                    }
                }
            }
        }
    }

    private fun setError(error: Throwable?) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.calendar_date_select) {
            showDatePicker()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDatePicker() {
        val builder = MaterialDatePicker.Builder.datePicker()
        val calendarConstraints = CalendarConstraints.Builder().setValidator(DayValidator()).build()
        val pickerBuilder = builder.setCalendarConstraints(calendarConstraints)
        if (viewModel.currentSelectedDate > 0) {
            pickerBuilder.setSelection(viewModel.currentSelectedDate)
        }
        val picker = pickerBuilder.build()
        picker.addOnPositiveButtonClickListener {
            viewModel.onDateClicked(it)
        }
        picker.show(childFragmentManager, "date_picker")
    }

}