package com.example.balaqai.game

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityAliAndAyyaBinding
import com.example.balaqai.game.data.QuestionsBank
import com.example.balaqai.game.data.QuestionsList
import com.example.balaqai.game.data.gameData
import com.example.balaqai.utils.GameData

class AliAndAyyaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAliAndAyyaBinding
    private var questionsAudio = mutableListOf<String>()
    private lateinit var question: String
    private val questionsBank: QuestionsBank = QuestionsBank()
    var mpCount = 1
    var mp = MediaPlayer()

    val listGameData = GameData.gameOfAliMenAiya
    val userSeletedAnswerList = mutableListOf<String>()

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

        Log.d("MyTag", "listGameData: ${listGameData.size}")
        getSelectedTopicName = intent.getStringExtra("selectedTopic").toString()

        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.tagam_ertegi)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.korme)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.otay)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.kiz_yzaty)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.kiiz_basy)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.ty_avtor)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.otan_bastay)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.tyda_jok)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.kara_soz)
        questionsAudio.add("android.resource://"+this.packageName+"/"+R.raw.korkit_ata)


    }

    override fun onStart() {
        super.onStart()

        questionsLists = questionsBank.getQuestion(getSelectedTopicName!!)
        Log.d("MyTag", "questionsLists: ${questionsLists.size}")

        setImageGlide(binding.imFirstAliAndAiya, "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant1}")
        setImageGlide(binding.imSecondAliAndAiya, "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant2}")
        setImageGlide(binding.imThirdAliAndAiya, "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant3}")
        setImageGlide(binding.imFourthAliAndAiya, "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant4}")

        op1 = listGameData[0].variant1
        op2 = listGameData[0].variant2
        op3 = listGameData[0].variant3
        op4 = listGameData[0].variant4
        binding.soundQues.setOnClickListener {

            if (mpCount%2 != 0) {
                mp.setDataSource(this, Uri.parse(questionsAudio[currentQuestionPosition]))
                mp.prepare()
                mpCount++
                mp.start()
            }else{
                mp.stop()
                mp.release()
                mp = MediaPlayer()
                mpCount = 1
            }
        }

        binding.option1.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = op1

                binding.option1.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                userSeletedAnswerList.add(selectedOptionByUser)

            }
        }

        binding.option2.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = op2

                binding.option2.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                userSeletedAnswerList.add(selectedOptionByUser)

            }
        }

        binding.option3.setOnClickListener {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = op3

                binding.option3.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                userSeletedAnswerList.add(selectedOptionByUser)

            }
        }

        binding.option4.setOnClickListener {
            if (selectedOptionByUser.isEmpty()){
                selectedOptionByUser = op4

                binding.option4.setBackgroundResource(R.drawable.round_back_red_10)

                revealAnswer()

                userSeletedAnswerList.add(selectedOptionByUser)

            }
        }


        binding.nextQuestionBtn.setOnClickListener {

            if (selectedOptionByUser.isEmpty()){
                Toast.makeText(this, "Бір нұсқаны таңда!", Toast.LENGTH_SHORT).show()
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
        mpCount = 1
        mp.stop()
        mp.release()
        mp = MediaPlayer()


        if ((currentQuestionPosition + 1) == listGameData.size){
            binding.nextQuestionBtn.text = "Ойынды аяқтау"
        }

        if (currentQuestionPosition < listGameData.size){
            if (listGameData[currentQuestionPosition].answer.equals("answer3.jpg")){
                Glide
                    .with(this)
                    .load(Uri.parse("${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/answer3.jpg"))
                    .fitCenter()
                    .into(binding.imFirstAliAndAiya)

                Glide
                    .with(this)
                    .load(Uri.parse("${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/variant9.jpg"))
                    .fitCenter()
                    .into(binding.imThirdAliAndAiya)


                setImageGlide(
                    binding.imSecondAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant2}"
                )
                setImageGlide(
                    binding.imFourthAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant4}"
                )
            } else  if (listGameData[currentQuestionPosition].answer.equals("answer4.jpg")){
                Glide
                    .with(this)
                    .load(Uri.parse("${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/variant15.jpg"))
                    .fitCenter()
                    .into(binding.imSecondAliAndAiya)

                Glide
                    .with(this)
                    .load(Uri.parse("${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/answer4.jpg"))
                    .fitCenter()
                    .into(binding.imThirdAliAndAiya)


                setImageGlide(
                    binding.imFirstAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant1}"
                )
                setImageGlide(
                    binding.imFourthAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant4}"
                )
            }  else {
                setImageGlide(
                    binding.imFirstAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant1}"
                )
                setImageGlide(
                    binding.imSecondAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant2}"
                )
                setImageGlide(
                    binding.imThirdAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant3}"
                )
                setImageGlide(
                    binding.imFourthAliAndAiya,
                    "${BalaqaiApi.BASE_URL}/game/aliMenAiyaImage/${GameData.gameOfAliMenAiya[currentQuestionPosition].variant4}"
                )
            }
            selectedOptionByUser = ""

            binding.option1.setBackgroundResource(R.drawable.round_back_white_10)
            binding.option2.setBackgroundResource(R.drawable.round_back_white_10)
            binding.option3.setBackgroundResource(R.drawable.round_back_white_10)
            binding.option4.setBackgroundResource(R.drawable.round_back_white_10)


            op1 = listGameData[currentQuestionPosition].variant1
            op2 = listGameData[currentQuestionPosition].variant2
            op3 = listGameData[currentQuestionPosition].variant3
            op4 = listGameData[currentQuestionPosition].variant4

        }
        else{
            alertDialog()
        }
    }

    private fun setImageGlide(iv: ImageView?, url: String) {

        iv?.let {
            Glide
                .with(this)
                .load(Uri.parse(url))
                .fitCenter()
                .into(it)
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

        for (i in listGameData.indices){
            val getUserSelectedAnswer = userSeletedAnswerList[i]
            val getAnswer = listGameData[i].answer

            if (getUserSelectedAnswer.equals(getAnswer)){
                correctAnswer++
            }
        }

        return correctAnswer
    }

    private fun getInCorrectAnswers(): Int{

        var correctAnswer = 0

        for (i in listGameData.indices){
            val getUserSelectedAnswer = userSeletedAnswerList[i]
            val getAnswer = listGameData[i].answer

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
        val getAnswer = listGameData[currentQuestionPosition].answer

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