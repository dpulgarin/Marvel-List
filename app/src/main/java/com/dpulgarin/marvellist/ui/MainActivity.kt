package com.dpulgarin.marvellist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dpulgarin.marvellist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}