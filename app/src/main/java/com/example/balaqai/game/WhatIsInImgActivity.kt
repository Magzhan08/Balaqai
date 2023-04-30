package com.example.balaqai.game

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Window
import android.widget.Button
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityWhatIsInImgBinding
import com.example.balaqai.game.adapter.GridViewAnswerAdapter
import com.example.balaqai.game.adapter.GridViewSuggestAdapter
import com.example.balaqai.game.data.Common
import com.example.balaqai.utils.GameData
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
                Toast.makeText(this, "Қате!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun setupList(image_count: Int) {
        if (image_count >= GameData.gameSuretteNeKorsetilgen.size) {
            alertDialog()
        } else {
            //Random logo

            Glide
                .with(this)
                .load(Uri.parse("${BalaqaiApi.BASE_URL}/game/suretteNeKorsetilgenImage/${GameData.gameSuretteNeKorsetilgen[image_count].image}"))
                .fitCenter()
                .into(binding.imLogo)

            correct_answer = GameData.gameSuretteNeKorsetilgen[image_count].name
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
    private fun alertDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.what_image_game_finish)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var btnNew = dialog.findViewById<Button>(R.id.new_game_what_image)
        var btnExit = dialog.findViewById<Button>(R.id.exit_game_what_image)
        btnNew.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(applicationContext, WhatIsInImgActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnExit.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }
}