package com.example.balaqai.game

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
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
    private lateinit var getSelectedTopicName: String
    var op1 = ""
    var op2 = ""
    var op3 = ""
    var op4 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAliAndAyyaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSelectedTopicName = intent.getStringExtra("selectedTopic").toString()

        binding.topicName.text = getSelectedTopicName

    }

    override fun onStart() {
        super.onStart()

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
            alertDialog()
        }
    }

    private fun alertDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.ali_and_ayya_game_finish)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var tvPoint : TextView = dialog.findViewById(R.id.point_aliandayya)
        tvPoint.text = "Дұрыс жауып саны: ${getCorrectAnswers()} \n Қате жауап саны ${getInCorrectAnswers()}"
        var tvMotivation : TextView = dialog.findViewById(R.id.motivation_aliandayya)
        if (getCorrectAnswers() > questionsLists.size/2) {
            tvMotivation.text = "Жарайсың балақай! \n Өте жақсы ойын болды!"
        } else{
            tvMotivation.text = "Жарайсың балақай! \n Осы қалпыңнан тайма!"
        }
        val btnExit: Button = dialog.findViewById(R.id.exit_game_ali_and_ayya)
        val btnNew: Button = dialog.findViewById(R.id.new_game_ali_and_ayya)
        btnExit.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        btnNew.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(applicationContext, AliAndAyyaActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()

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