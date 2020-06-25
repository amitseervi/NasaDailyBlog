package com.amit.nasablog.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.amit.nasablog.R
import com.amit.nasablog.databinding.ActivityMainBinding
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), HasAndroidInjector {
    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
