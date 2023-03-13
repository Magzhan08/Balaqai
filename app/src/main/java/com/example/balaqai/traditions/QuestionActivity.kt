package com.example.balaqai.traditions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityQuestionBinding
import com.example.balaqai.game.data.QuestionsList
import com.example.balaqai.traditions.data.QuestionAndAnswer

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding

    private var questionsLists: List<QuestionAndAnswer> = mutableListOf(QuestionAndAnswer("Шілдеханаға жиналушылар қандай құттықтау сөз айтады?" ,"Туған күніңмен", "Бақытты бол", "Бауы берік болсын","Б",""))

    private var correctAnswer: String = questionsLists[0].answer
    private var selectedOptionByUser = ""
    private var currentQuestionPosition = 0

    var op1 = ""
    var op2 = ""
    var op3 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }


        binding.question.text = questionsLists[0].question
        binding.option1Text.text = questionsLists[0].option1
        binding.option2Text.text = questionsLists[0].option2
        binding.option3Text.text = questionsLists[0].option3

        op1 = questionsLists[0].option1
        op2 = questionsLists[0].option2
        op3 = questionsLists[0].option3

        binding.option1.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = op1

                binding.option1.setBackgroundResource(R.drawable.ques_incorrect_ans_style)

                revealAnswer()

                questionsLists[currentQuestionPosition].userSeletedAnswer = selectedOptionByUser

            }
        }

        binding.option2.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = questionsLists[0].option2

                binding.option2.setBackgroundResource(R.drawable.ques_incorrect_ans_style)

                revealAnswer()

                questionsLists[currentQuestionPosition].userSeletedAnswer = selectedOptionByUser

            }
        }

        binding.option3.setOnClickListener {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = questionsLists[0].option3

                binding.option3.setBackgroundResource(R.drawable.ques_incorrect_ans_style)

                revealAnswer()

                questionsLists[currentQuestionPosition].userSeletedAnswer = selectedOptionByUser

            }
        }

        binding.buttonEnd.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this@QuestionActivity, TraditionsActivity::class.java)
                startActivity(intent)
            }
        }

    }


    private fun revealAnswer(){
        val getAnswer = correctAnswer

        if ("А".equals(getAnswer)){
            binding.option1.setBackgroundResource(R.drawable.ques_correct_ans_style)
        }
        else if ("Ә".equals(getAnswer)){
            binding.option2.setBackgroundResource(R.drawable.ques_correct_ans_style)
        }
        else if ("Б".equals(getAnswer)){
            binding.option3.setBackgroundResource(R.drawable.ques_correct_ans_style)
        }

    }


    companion object{
        private const val EXTRA_NAME_OF_TRADITION = "nameTrad"

        fun newIntent(context: Context, nameTradition: String): Intent {
            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra(EXTRA_NAME_OF_TRADITION,nameTradition)
            return intent
        }
    }
}