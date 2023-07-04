package com.doganur.shoppingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.doganur.shoppingapp.common.viewBinding
import com.doganur.shoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}