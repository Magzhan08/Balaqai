package com.example.balaqai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.balaqai.authorization.MainActivity
import com.example.balaqai.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide)

        binding.imQoshKeldin.startAnimation(slideAnimation)
        binding.imBalaqai.startAnimation(slideAnimation)
        binding.imOrnek.startAnimation(slideAnimation)
        binding.imIcons.startAnimation(slideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        },2000)

    }
}