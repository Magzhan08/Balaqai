package com.example.balaqai.game

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityWhatIsInImgBinding
import com.example.balaqai.game.adapter.GridViewAnswerAdapter
import com.example.balaqai.game.adapter.GridViewSuggestAdapter
import com.example.balaqai.game.data.Common
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import kotlin.random.Random

class WhatIsInImgActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWhatIsInImgBinding

    private var imageCounter = 0
    var suggestSource = mutableListOf<String>()
    lateinit var answerAdapter: GridViewAnswerAdapter
    lateinit var suggestAdapter: GridViewSuggestAdapter

    lateinit var gridViewAnswer: GridView
    lateinit var gridViewSuggest: GridView


    var image_list = listOf(
        R.drawable.dombira,
        R.drawable.ty,
        R.drawable.shanirak,
        R.drawable.tenge
    )

    var common = Common()

    lateinit var answer: CharArray
    lateinit var correct_answer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWhatIsInImgBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }

        initView()
    }

    private fun initView() {

        gridViewAnswer = findViewById<GridView>(R.id.gridViewAnswer)
        gridViewSuggest = findViewById<GridView>(R.id.gridViewSuggest)

        //Add SetupList here
        setupList(imageCounter)

        binding.btnSubmit.setOnClickListener {
            var result = ""
            for (i in 0 until common.user_submit_answer.size) {
                result += common.user_submit_answer[i].toString()
            }

            if (result == correct_answer) {
                Toast.makeText(
                    applicationContext,
                    "Finish! This is $result",
                    Toast.LENGTH_SHORT
                ).show()

                imageCounter++

                //Reset
                common.count = 0
                common.user_submit_answer = CharArray(correct_answer.length)

                //Set Adapter
                var answerAdapter = GridViewAnswerAdapter(setUpNullList(), applicationContext)
                gridViewAnswer.adapter = answerAdapter
                answerAdapter.notifyDataSetChanged()

                var suggestAdapter = GridViewSuggestAdapter(
                    suggestSource,
                    applicationContext,
                    this@WhatIsInImgActivity
                )
                gridViewSuggest.adapter = suggestAdapter
                suggestAdapter.notifyDataSetChanged()

                setupList(imageCounter)
            } else {
                Toast.makeText(this, "Incorrect!!!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setupList(image_count: Int) {
        if (image_count >= image_list.size) {
            val intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            //Random logo
            var imageSelected = image_list[image_count]
            binding.imLogo.setImageResource(imageSelected)

            correct_answer = resources.getResourceName(imageSelected)
            correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/") + 1)

            answer = correct_answer.toCharArray()

            common.user_submit_answer = CharArray(answer.size)

            //Add answer character to list
            suggestSource.clear()
            for (i in answer) {
                suggestSource.add(i.toString())
            }

            //Random add some character
            for (i in answer.size until answer.size + 4) {
                suggestSource.add(common.alphabet_character[Random.nextInt(common.alphabet_character.size)])
            }

            //Sort random
            suggestSource.shuffle()

            //Set for GridView
            answerAdapter = GridViewAnswerAdapter(setUpNullList(), this)
            suggestAdapter = GridViewSuggestAdapter(suggestSource, this, this)

            answerAdapter.notifyDataSetChanged()
            suggestAdapter.notifyDataSetChanged()

            gridViewSuggest.adapter = suggestAdapter
            gridViewAnswer.adapter = answerAdapter

        }
    }

    private fun setUpNullList(): CharArray {
        var result = CharArray(answer.size)
        for (i in answer.indices) {
            result[i] = ' '
        }
        return result
    }
}