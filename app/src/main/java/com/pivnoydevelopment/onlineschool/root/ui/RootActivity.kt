package com.pivnoydevelopment.onlineschool.root.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pivnoydevelopment.onlineschool.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}