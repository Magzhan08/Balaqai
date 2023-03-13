package com.example.balaqai.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityAliAndAyyaBinding
import com.example.balaqai.game.data.QuestionsBank
import com.example.balaqai.game.data.QuestionsList

class AliAndAyyaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAliAndAyyaBinding
    private lateinit var questions: String
    private lateinit var question: String
    private val questionsBank: QuestionsBank = QuestionsBank()

    private var selectedOptionByUser = ""
    private var currentQuestionPosition = 0
    private lateinit var questionsLists: List<QuestionsList>

    var op1 = ""
    var op2 = ""
    var op3 = ""
    var op4 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAliAndAyyaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getSelectedTopicName = intent.getStringExtra("selectedTopic")

        binding.topicName.text = getSelectedTopicName

        questionsLists = questionsBank.getQuestion(getSelectedTopicName!!)

        binding.questions.text = "${currentQuestionPosition+1}/${questionsLists.size}"
        binding.question.text = "${questionsLists[0].question}"

        op1 = questionsLists[0].option1
        op2 = questionsLists[0].option2
        op3 = questionsLists[0].option3
        op4 = questionsLists[0].option4

        binding.option1.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = op1

                binding.option1.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                questionsLists[currentQuestionPosition].userSeletedAnswer = selectedOptionByUser

            }
        }

        binding.option2.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = questionsLists[0].option2

                binding.option2.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                questionsLists[currentQuestionPosition].userSeletedAnswer = selectedOptionByUser

            }
        }

        binding.option3.setOnClickListener {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = questionsLists[0].option3

                binding.option3.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                questionsLists[currentQuestionPosition].userSeletedAnswer = selectedOptionByUser

            }
        }

        binding.option4.setOnClickListener {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = questionsLists[0].option4

                binding.option4.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                questionsLists[currentQuestionPosition].userSeletedAnswer = selectedOptionByUser

            }
        }


        binding.nextQuestionBtn.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
            else{
                changeNextQuestion()
            }
        }

        binding.backIcon.setOnClickListener {
            startActivity(Intent(this, GamesActivity::class.java))
            finish()
        }

    }

    private fun changeNextQuestion(){
        currentQuestionPosition++

        if ((currentQuestionPosition + 1) == questionsLists.size){
            binding.nextQuestionBtn.text = "Ойынды аяқтау"
        }

        if (currentQuestionPosition < questionsLists.size){
            selectedOptionByUser = ""

            binding.option1.setBackgroundResource(R.drawable.round_back_white_10)
            binding.option2.setBackgroundResource(R.drawable.round_back_white_10)
            binding.option3.setBackgroundResource(R.drawable.round_back_white_10)
            binding.option4.setBackgroundResource(R.drawable.round_back_white_10)

            binding.questions.text = "${currentQuestionPosition+1}/${questionsLists.size}"
            binding.question.text = "${questionsLists[currentQuestionPosition].question}"

            op1 = questionsLists[currentQuestionPosition].option1
            op2 = questionsLists[currentQuestionPosition].option2
            op3 = questionsLists[currentQuestionPosition].option3
            op4 = questionsLists[currentQuestionPosition].option4

        }
        else{
            val intent = Intent(this,AliAndAyyaResultActivity::class.java)
            intent.putExtra("correct",getCorrectAnswers())
            intent.putExtra("incorrect",getInCorrectAnswers())

            startActivity(intent)

            finish()

        }
    }

    private fun getCorrectAnswers(): Int{

        var correctAnswer = 0

        for (i in questionsLists.indices){
            val getUserSelectedAnswer = questionsLists[i].userSeletedAnswer
            val getAnswer = questionsLists[i].answer

            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer++
            }
        }

        return correctAnswer
    }

    private fun getInCorrectAnswers(): Int{

        var correctAnswer = 0

        for (i in questionsLists.indices){
            val getUserSelectedAnswer = questionsLists[i].userSeletedAnswer
            val getAnswer = questionsLists[i].answer

            if (!getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer++
            }
        }

        return correctAnswer
    }

    override fun onBackPressed() {
        startActivity(Intent(this, GamesActivity::class.java))
        finish()
    }

    private fun revealAnswer(){
        val getAnswer = questionsLists[currentQuestionPosition].answer

        if (op1.equals(getAnswer)){
            binding.option1.setBackgroundResource(R.drawable.round_back_green_10)
        }
        else if (op2.equals(getAnswer)){
            binding.option2.setBackgroundResource(R.drawable.round_back_green_10)
        }
        else if (op3.equals(getAnswer)){
            binding.option3.setBackgroundResource(R.drawable.round_back_green_10)
        }
        else if (op4.equals(getAnswer)){
            binding.option4.setBackgroundResource(R.drawable.round_back_green_10)
        }

    }
}