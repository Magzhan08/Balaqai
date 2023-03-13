package com.example.balaqai.traditions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityTraditionBinding

class TraditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTraditionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_NAME_OF_TRADITION)){
            finish()
            return
        }
        val nameTradition = intent.getStringExtra(EXTRA_NAME_OF_TRADITION)

        binding.nameOfTradition.text = nameTradition
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.buttonStart.setOnClickListener {
            val intent = InfoTrActivity.newIntent(this@TraditionActivity,nameTradition.toString())
            startActivity(intent)
        }
    }

    companion object{
        private const val EXTRA_NAME_OF_TRADITION = "nameTrad"

        fun newIntent(context: Context, nameTradition: String): Intent {
            val intent = Intent(context,TraditionActivity::class.java)
            intent.putExtra(EXTRA_NAME_OF_TRADITION,nameTradition)
            return intent
        }
    }
}