package com.example.balaqai.traditions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityVideoTrBinding

class VideoTrActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoTrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoTrBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.buttonNext.setOnClickListener {
            val intent = QuestionActivity.newIntent(this@VideoTrActivity,"SOMETHING")
            startActivity(intent)
        }
    }
    companion object{
        private const val EXTRA_NAME_OF_TRADITION = "nameTrad"

        fun newIntent(context: Context, nameTradition: String): Intent {
            val intent = Intent(context,VideoTrActivity::class.java)
            intent.putExtra(EXTRA_NAME_OF_TRADITION,nameTradition)
            return intent
        }
    }
}