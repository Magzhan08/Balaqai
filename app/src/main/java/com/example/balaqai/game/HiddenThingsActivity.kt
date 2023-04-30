package com.example.balaqai.game

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityHiddenThingsBinding

class HiddenThingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHiddenThingsBinding
    private var point = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHiddenThingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        var mpCount = 1
        var mp = MediaPlayer()
        binding.btnSound.setOnClickListener {
            if (mpCount%2 != 0) {
                mp.setDataSource(this, Uri.parse("android.resource://"+this.packageName+"/"+R.raw.hidden_things_task))
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

        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }
        binding.imKilish.setOnClickListener {
            point++
            binding.imKilish.visibility = View.INVISIBLE
        }
        binding.imKilem.setOnClickListener {
            point++
            binding.imKilem.visibility = View.INVISIBLE
        }
        binding.imSadak.setOnClickListener {
            point++
            binding.imSadak.visibility = View.INVISIBLE
        }
        binding.imKamshi.setOnClickListener {
            point++
            binding.imKamshi.visibility = View.INVISIBLE
        }
        binding.imKobuz.setOnClickListener {
            point++
            binding.imKobuz.visibility = View.INVISIBLE
        }
        binding.imTakia.setOnClickListener {
            point++
            binding.imTakia.visibility = View.INVISIBLE
        }
        binding.imTamak.setOnClickListener {
            point++
            binding.imTamak.visibility = View.INVISIBLE
        }
        binding.imBaursak.setOnClickListener {
            point++
            binding.imBaursak.visibility = View.INVISIBLE
        }
        binding.imBilezik.setOnClickListener {
            point++
            binding.imBilezik.visibility = View.INVISIBLE
        }
        binding.imBesik.setOnClickListener {
            point++
            binding.imBesik.visibility = View.INVISIBLE
        }

        binding.btnSubmit.setOnClickListener {
            alertDialog()
        }

    }

    private fun alertDialog(){
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.hidden_things_game_finish)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var tvPoint: TextView = dialog.findViewById(R.id.point_hidden_things)
        var tvMotivation: TextView = dialog.findViewById(R.id.motivation_hidden_things)
        tvPoint.text = "Ұпай саны: $point / 10 "
        if (point == 10){
            tvMotivation.text = "Жарайсың балақай! \n Барлығын дұрыс таптың!"
        }else if (point > 6) {
            tvMotivation.text = "Жарайсың балақай! \n Өте жақсы ойын болды!"
        } else{
            tvMotivation.text = "Жарайсың балақай! \n Осы қалпыңнан тайма!"
        }
        var btnNew = dialog.findViewById<Button>(R.id.new_game_hidden_things)
        var btnExit = dialog.findViewById<Button>(R.id.exit_game_hidden_things)

        btnNew.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(applicationContext, HiddenThingsActivity::class.java)
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