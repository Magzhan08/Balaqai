package com.example.balaqai.traditions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityTraditionBinding
import com.example.balaqai.utils.TraditionsData

class TraditionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTraditionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraditionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_NAME_OF_TRADITION) || !intent.hasExtra(EXTRA_NAME_TRADITION_GROUP)){
            finish()
            return
        }
        val nameTradition = intent.getStringExtra(EXTRA_NAME_OF_TRADITION)
        val nameOfTrGroup = intent.getStringExtra(EXTRA_NAME_TRADITION_GROUP)
        if (nameOfTrGroup != null) {
            TraditionsData.getTraditionsGroup(nameOfTrGroup)
        }


        for (tradition in TraditionsData.selectedTraditionList){
            if (tradition.name.replace("\n","") == nameTradition){
                binding.nameOfTradition.text = nameTradition

                when (nameTradition) {
                    "Жарапазан" -> {
                        binding.imTradition.setImageResource(R.drawable.jarapazan)
                    }
                    "Наурыз той" -> {
                        binding.imTradition.setImageResource(R.drawable.nauryz_toi)
                    }
                    "Тұсаукесер" -> {
                        binding.imTradition.setImageResource(R.drawable.tusaykeser)
                    }
                    "Келін түсіру" -> {
                        binding.imTradition.setImageResource(R.drawable.kelin_tusiry)
                    }
                    else -> {
                        Glide
                            .with(this)
                            .load(Uri.parse("${TraditionsData.imageUrl}${tradition.image}"))
                            .fitCenter()
                            .into(binding.imTradition)
                    }
                }

            }
        }


        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }
        binding.buttonStart.setOnClickListener {
            val intent = InfoTrActivity.newIntent(this@TraditionActivity,nameTradition.toString(),nameOfTrGroup.toString())
            startActivity(intent)
        }
    }

    companion object{
        private const val EXTRA_NAME_OF_TRADITION = "nameTrad"
        private const val EXTRA_NAME_TRADITION_GROUP = "nameOfTraditionGroup"

        fun newIntent(context: Context, nameTradition: String, nameTraditionGroup: String): Intent {
            val intent = Intent(context,TraditionActivity::class.java)
            intent.putExtra(EXTRA_NAME_OF_TRADITION,nameTradition)
            intent.putExtra(EXTRA_NAME_TRADITION_GROUP,nameTraditionGroup)
            return intent
        }
    }
}