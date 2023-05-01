package com.example.balaqai.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import com.example.balaqai.R
import com.example.balaqai.authorization.MainActivity
import com.example.balaqai.databinding.ActivityGamesBinding
import com.example.balaqai.traditions.ProfileAndSettings
import com.example.balaqai.traditions.TraditionsActivity
import com.example.balaqai.utils.GameData
import com.example.balaqai.utils.SharedPref
import com.example.balaqai.viewModel.AliMenAiyaViewModel
import com.example.balaqai.viewModel.SuretteNeKorsetilgenViewModel

class GamesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGamesBinding
    private var selectedTopicName = ""
    private lateinit var sharePref: SharedPreferences
    val gameOfAliMenAiyaViewModel: AliMenAiyaViewModel by viewModels()
    val gameOfSuretteNeKorsetilgenViewModel: SuretteNeKorsetilgenViewModel by viewModels()

    var mp = MediaPlayer()
    var mpCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)



        selectedSettings()



        binding.whatisinImgLayout.setOnClickListener {

            selectedTopicName = "WhatIsInImg"


            val intent = Intent(this, WhatIsInImgActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicName)
            startActivity(intent)
        }

        binding.aliAndAyyaLayout.setOnClickListener {

            selectedTopicName = "AliAndAyya"

            val intent = Intent(this, AliAndAyyaActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicName)
            startActivity(intent)
        }

        binding.hiddenThingsLayout.setOnClickListener {

            selectedTopicName = "HiddenThings"
            val intent = Intent(this, HiddenThingsActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicName)
            startActivity(intent)
        }
        binding.collateLayout.setOnClickListener {

            selectedTopicName = "Collate"

            val intent = Intent(this, CollateGameActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicName)
            startActivity(intent)

        }



    }

    override fun onStart() {
        super.onStart()
        binding.balaSound.setOnClickListener {
           playSound("android.resource://"+this.packageName+"/"+R.raw.bala_calling)
        }
        binding.imAliAndAyyaSound.setOnClickListener {
            playSound("android.resource://"+this.packageName+"/"+R.raw.ali_and_aiya_audio)
        }
        binding.imCollateSound.setOnClickListener {
            playSound("android.resource://"+this.packageName+"/"+R.raw.collate_audio)
        }
        binding.imHiddenThingsSound.setOnClickListener {
            playSound("android.resource://"+this.packageName+"/"+R.raw.hiddenthings_audio)
        }
        binding.imWhatIsInImgSound.setOnClickListener {
            playSound("android.resource://"+this.packageName+"/"+R.raw.whatisinimg_audio)
        }

        sharePref = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)
        binding.userName.text = SharedPref.getUserName(sharePref)
        binding.btnOpenProfile.setOnClickListener {

            ProfileAndSettings.showProfileDialog(this,
                SharedPref.getUserName(sharePref),
                SharedPref.getUserAge(sharePref), SharedPref.getUserEmail(sharePref))
        }

        binding.settings.setOnClickListener {
            if (binding.settingsPicker.isShown){
                closeSettings()
            } else{
                openSettings()
            }
        }

        binding.btnTraditions.setOnClickListener {
                val intent = Intent(this, TraditionsActivity::class.java)
                startActivity(intent)
        }


        gameOfAliMenAiyaViewModel.gameOfAliMenAiya()
        gameOfSuretteNeKorsetilgenViewModel.gameSuretteNeKorsetilgen()

        gameOfAliMenAiyaViewModel.gameAliMenAiyaList.observe(this@GamesActivity){gameOfAliMenAiya ->
            if (gameOfAliMenAiya != null){
                GameData.getGameOfAliMenAiya(gameOfAliMenAiya)
            }
        }

        gameOfSuretteNeKorsetilgenViewModel.dataList.observe(this@GamesActivity){gameOfSuretteNeKorsetilgen ->
            if (gameOfSuretteNeKorsetilgen != null) {
                GameData.getSuretteNeKorsetilgen(gameOfSuretteNeKorsetilgen)
            }
        }

    }

    private fun playSound(url: String){
            if (mpCount%2 != 0) {
                mp.setDataSource(this, Uri.parse(url))
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

    private fun openSettings(){
        binding.settingsPicker.visibility = View.VISIBLE
        val openAnim = AnimationUtils.loadAnimation(this,R.anim.open_settings_picker)
        binding.settingsPicker.startAnimation(openAnim)
    }
    private fun closeSettings(){
        val openAnim = AnimationUtils.loadAnimation(this,R.anim.close_settings_picker)
        openAnim.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                binding.settingsPicker.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })
        binding.settingsPicker.startAnimation(openAnim)
    }

    private fun selectedSettings(){
        binding.imBell.setOnClickListener {

        }
        binding.imExit.setOnClickListener {
            sharePref.edit().clear().apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.imGuitar.setOnClickListener {

        }
        binding.imSound.setOnClickListener {

        }
    }

}