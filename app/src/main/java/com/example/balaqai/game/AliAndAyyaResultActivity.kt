package com.example.balaqai.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityAliAndAyyaResultBinding

class AliAndAyyaResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAliAndAyyaResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAliAndAyyaResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getCorrectAnswer = intent.getIntExtra("correct",0)
        val getInCorrectAnswer = intent.getIntExtra("incorrect",0)

        binding.correctAnswer.text = getCorrectAnswer.toString()
        binding.incorrectAnswer.text = getInCorrectAnswer.toString()

        binding.theEndGame.setOnClickListener {
            startActivity(Intent(this, GamesActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, GamesActivity::class.java))
        finish()
    }
}