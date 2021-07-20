package com.froggie.design.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.froggie.design.MainActivity
import com.froggie.design.R
import com.froggie.design.databinding.ActivitySplashBinding

private const val SPLASH_TIMEOUT : Long = 3000

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(R.drawable.froggie_main).into(binding.splashImage)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity :: class.java))
        }, SPLASH_TIMEOUT)
    }
}