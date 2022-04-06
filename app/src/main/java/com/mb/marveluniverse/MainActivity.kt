package com.mb.marveluniverse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mb.viewmodel.homepage.HomePageFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mb.viewmodel.homepage.HomepageViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : HomepageViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val a = viewModel.get()
        Log.i("deneme",a.toString().length.toString())
    }
    }


