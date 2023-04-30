package com.example.balaqai.game

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityCollateGameBinding
import com.example.balaqai.utils.TraditionsData
import java.util.*

class CollateGameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollateGameBinding

    lateinit var iv_11: ImageView
    lateinit var iv_12: ImageView
    lateinit var iv_13: ImageView
    lateinit var iv_14: ImageView
    lateinit var iv_21: ImageView
    lateinit var iv_22: ImageView
    lateinit var iv_23: ImageView
    lateinit var iv_24: ImageView
    lateinit var iv_31: ImageView
    lateinit var iv_32: ImageView
    lateinit var iv_33: ImageView
    lateinit var iv_34: ImageView
    //Array for the images
    var cardArray: IntArray = intArrayOf(101,102,103,104,105,106,201,202,203,204,205,206)

    var image101 = ""
    var image102 = ""
    var image103 = ""
    var image104 = ""
    var image105 = ""
    var image106 = ""
    var image201 = ""
    var image202 = ""
    var image203 = ""
    var image204 = ""
    var image205 = ""
    var image206 = ""


    var firstCard = 0
    var secondCard = 0
    var clickedFirst = 0
    var clickedSecond = 0

    var cardNumber = 1
    var turn = 1
    var playerPoints = 0
    var counter = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCollateGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSubmit.setOnClickListener {
            alertDialog()
        }
        iv_11 = findViewById(R.id.iv_11)
        iv_12 = findViewById(R.id.iv_12)
        iv_13 = findViewById(R.id.iv_13)
        iv_14 = findViewById(R.id.iv_14)
        iv_21 = findViewById(R.id.iv_21)
        iv_22 = findViewById(R.id.iv_22)
        iv_23 = findViewById(R.id.iv_23)
        iv_24 = findViewById(R.id.iv_24)
        iv_31 = findViewById(R.id.iv_31)
        iv_32 = findViewById(R.id.iv_32)
        iv_33 = findViewById(R.id.iv_33)
        iv_34 = findViewById(R.id.iv_34)

        iv_11.tag = "0"
        iv_12.tag = "1"
        iv_13.tag = "2"
        iv_14.tag = "3"
        iv_21.tag = "4"
        iv_22.tag = "5"
        iv_23.tag = "6"
        iv_24.tag = "7"
        iv_31.tag = "8"
        iv_32.tag = "9"
        iv_33.tag = "10"
        iv_34.tag = "11"

        //load the cards images
        frontOfCardsResources()

        //shuffle the images
        //Collections.shuffle(Arrays.asList(cardArray))

        cardArray.shuffle()

        //changing the color of the second player (inactive)

        iv_11.setOnClickListener {
            var theCard: Int = (iv_11.tag as String).toInt()
            doStuff(iv_11,theCard)
        }
        iv_12.setOnClickListener {
            var theCard: Int = (iv_12.tag as String).toInt()
            doStuff(iv_12,theCard)
        }
        iv_13.setOnClickListener {
            var theCard: Int = (iv_13.tag as String).toInt()
            doStuff(iv_13,theCard)
        }
        iv_14.setOnClickListener {
            var theCard: Int = (iv_14.tag as String).toInt()
            doStuff(iv_14,theCard)
        }
        iv_21.setOnClickListener {
            var theCard: Int = (iv_21.tag as String).toInt()
            doStuff(iv_21,theCard)
        }
        iv_22.setOnClickListener {

            var theCard: Int = (iv_22.tag as String).toInt()
            doStuff(iv_22,theCard)

        }
        iv_23.setOnClickListener {

            var theCard: Int = (iv_23.tag as String).toInt()
            doStuff(iv_23,theCard)
        }
        iv_24.setOnClickListener {

            var theCard: Int = (iv_24.tag as String).toInt()
            doStuff(iv_24,theCard)
        }
        iv_31.setOnClickListener {

            var theCard: Int = (iv_31.tag as String).toInt()
            doStuff(iv_31,theCard)
        }
        iv_32.setOnClickListener {

            var theCard: Int = (iv_32.tag as String).toInt()
            doStuff(iv_32,theCard)
        }
        iv_33.setOnClickListener {

            var theCard: Int = (iv_33.tag as String).toInt()
            doStuff(iv_33,theCard)
        }
        iv_34.setOnClickListener {

            var theCard: Int = (iv_34.tag as String).toInt()
            doStuff(iv_34,theCard)
        }
    }

    private fun setImageGlide(iv: ImageView?,url: String) {
        iv?.let {
            Glide
                .with(this)
                .load(Uri.parse(url))
                .fitCenter()
                .into(it)
        }
    }

    private fun doStuff(iv: ImageView?, theCard: Int) {
        counter++
        //set the correct image to the imageView
        if (cardArray[theCard] == 101){
            setImageGlide(iv,image101)
        } else if (cardArray[theCard] == 102){
            setImageGlide(iv,image102)
        } else if (cardArray[theCard] == 103){
            setImageGlide(iv,image103)
        } else if (cardArray[theCard] == 104){
            setImageGlide(iv,image104)
        } else if (cardArray[theCard] == 105){
            setImageGlide(iv,image105)
        } else if (cardArray[theCard] == 106){
            setImageGlide(iv,image106)
        } else if (cardArray[theCard] == 201){
            setImageGlide(iv,image201)
        } else if (cardArray[theCard] == 202){
            setImageGlide(iv,image202)
        } else if (cardArray[theCard] == 203){
            setImageGlide(iv,image203)
        } else if (cardArray[theCard] == 204){
            setImageGlide(iv,image204)
        } else if (cardArray[theCard] == 205){
            setImageGlide(iv,image205)
        } else if (cardArray[theCard] == 206){
            setImageGlide(iv,image206)
        }

        // check which image is selected and save it to temporary variable
        if (cardNumber == 1){
            firstCard = cardArray[theCard]
            if (firstCard > 200){
                firstCard -= 100
            }
            cardNumber = 2
            clickedFirst = theCard

            iv?.isEnabled = false
        } else if (cardNumber == 2){
            secondCard = cardArray[theCard]
            if (secondCard > 200){
                secondCard -= 100
            }
            cardNumber = 1
            clickedSecond = theCard

            iv_11.isEnabled = false
            iv_12.isEnabled = false
            iv_13.isEnabled = false
            iv_14.isEnabled = false
            iv_21.isEnabled = false
            iv_22.isEnabled = false
            iv_23.isEnabled = false
            iv_24.isEnabled = false
            iv_31.isEnabled = false
            iv_32.isEnabled = false
            iv_33.isEnabled = false
            iv_34.isEnabled = false

            val handler = Handler()
            handler.postDelayed({
                //check if the selected images are equal
                calculate()
                                }, 1000)

        }
    }

    private fun calculate() {
        //if images are equal remove them and add point
        if (firstCard == secondCard){
            if (clickedFirst == 0){
                iv_11.visibility = View.INVISIBLE
            } else if (clickedFirst == 1){
                iv_12.visibility = View.INVISIBLE
            } else if (clickedFirst == 2){
                iv_13.visibility = View.INVISIBLE
            } else if (clickedFirst == 3){
                iv_14.visibility = View.INVISIBLE
            } else if (clickedFirst == 4){
                iv_21.visibility = View.INVISIBLE
            } else if (clickedFirst == 5){
                iv_22.visibility = View.INVISIBLE
            } else if (clickedFirst == 6){
                iv_23.visibility = View.INVISIBLE
            } else if (clickedFirst == 7){
                iv_24.visibility = View.INVISIBLE
            } else if (clickedFirst == 8){
                iv_31.visibility = View.INVISIBLE
            } else if (clickedFirst == 9){
                iv_32.visibility = View.INVISIBLE
            } else if (clickedFirst == 10){
                iv_33.visibility = View.INVISIBLE
            } else if (clickedFirst == 11){
                iv_34.visibility = View.INVISIBLE
            }


            if (clickedSecond == 0){
                iv_11.visibility = View.INVISIBLE
            } else if (clickedSecond == 1){
                iv_12.visibility = View.INVISIBLE
            } else if (clickedSecond == 2){
                iv_13.visibility = View.INVISIBLE
            } else if (clickedSecond == 3){
                iv_14.visibility = View.INVISIBLE
            } else if (clickedSecond == 4){
                iv_21.visibility = View.INVISIBLE
            } else if (clickedSecond == 5){
                iv_22.visibility = View.INVISIBLE
            } else if (clickedSecond == 6){
                iv_23.visibility = View.INVISIBLE
            } else if (clickedSecond == 7){
                iv_24.visibility = View.INVISIBLE
            } else if (clickedSecond == 8){
                iv_31.visibility = View.INVISIBLE
            } else if (clickedSecond == 9){
                iv_32.visibility = View.INVISIBLE
            } else if (clickedSecond == 10){
                iv_33.visibility = View.INVISIBLE
            } else if (clickedSecond == 11){
                iv_34.visibility = View.INVISIBLE
            }

            // add points to the correct player
            if (turn == 1){
                playerPoints++
            }
        } else {
            iv_11.setImageResource(R.drawable.collate_back_img)
            iv_12.setImageResource(R.drawable.collate_back_img)
            iv_13.setImageResource(R.drawable.collate_back_img)
            iv_14.setImageResource(R.drawable.collate_back_img)
            iv_21.setImageResource(R.drawable.collate_back_img)
            iv_22.setImageResource(R.drawable.collate_back_img)
            iv_23.setImageResource(R.drawable.collate_back_img)
            iv_24.setImageResource(R.drawable.collate_back_img)
            iv_31.setImageResource(R.drawable.collate_back_img)
            iv_32.setImageResource(R.drawable.collate_back_img)
            iv_33.setImageResource(R.drawable.collate_back_img)
            iv_34.setImageResource(R.drawable.collate_back_img)

        }
        iv_11.isEnabled = true
        iv_12.isEnabled = true
        iv_13.isEnabled = true
        iv_14.isEnabled = true
        iv_21.isEnabled = true
        iv_22.isEnabled = true
        iv_23.isEnabled = true
        iv_24.isEnabled = true
        iv_31.isEnabled = true
        iv_32.isEnabled = true
        iv_33.isEnabled = true
        iv_34.isEnabled = true

        //check if the game is over
        checkEnd()
    }

    private fun checkEnd() {
        if (iv_11.visibility == View.INVISIBLE &&
            iv_12.visibility == View.INVISIBLE &&
            iv_13.visibility == View.INVISIBLE &&
            iv_14.visibility == View.INVISIBLE &&
            iv_21.visibility == View.INVISIBLE &&
            iv_22.visibility == View.INVISIBLE &&
            iv_23.visibility == View.INVISIBLE &&
            iv_24.visibility == View.INVISIBLE &&
            iv_31.visibility == View.INVISIBLE &&
            iv_32.visibility == View.INVISIBLE &&
            iv_33.visibility == View.INVISIBLE &&
            iv_34.visibility == View.INVISIBLE){

            alertDialog()
        }
    }

    private fun alertDialog() {

        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.collate_game_finish)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var tvPoint : TextView = dialog.findViewById(R.id.point)
        tvPoint.text = "Ұпай саны: $playerPoints / 6  \n Таңдалғын жұп саны: ${counter/2}"
        var tvMotivation : TextView = dialog.findViewById(R.id.motivation)
        if (playerPoints > cardArray.size/2) {
            tvMotivation.text = "Жарайсың балақай! \n Өте жақсы ойын болды!"
        } else{
            tvMotivation.text = "Жарайсың балақай! \n Осы қалпыңнан тайма!"
        }
        val btnExit: Button = dialog.findViewById(R.id.exit_game)
        val btnNew: Button = dialog.findViewById(R.id.new_game)
        btnExit.setOnClickListener {
            dialog.dismiss()
            finish()
        }
        btnNew.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(applicationContext, CollateGameActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()

    }

    private fun frontOfCardsResources() {
        image101 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/qurt.jpg"
        image102 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/Aitys.jpg"
        image103 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/asyq.jpg"
        image104 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/dombyra.jpg"
        image105 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/kokpar.jpg"
        image106 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/erToqym.jpg"
        image201 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/qurt.jpg"
        image202 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/Aitys.jpg"
        image203 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/asyq.jpg"
        image204 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/dombyra.jpg"
        image205 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/kokpar.jpg"
        image206 = "${BalaqaiApi.BASE_URL}/game/saikestendiruImage/erToqym.jpg"
    }



}