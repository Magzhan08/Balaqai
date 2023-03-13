package com.example.balaqai.game

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.balaqai.R
import com.example.balaqai.authorization.MainActivity
import com.example.balaqai.databinding.ActivityGamesBinding
import com.example.balaqai.traditions.ProfileAndSettings
import com.example.balaqai.utils.SharedPref

class GamesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGamesBinding
    private var selectedTopicName = ""
    private lateinit var sharePref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)



        selectedSettings()



        binding.whatisinImgLayout.setOnClickListener {

            selectedTopicName = "WhatIsInImg"

//            binding.whatisinImgLayoutConst.setBackgroundResource(R.drawable.round_back_white_stroke_20)
//
//            binding.aliAndAyyaLayoutConstr.setBackgroundResource(R.drawable.game_inner_layout_style)
//            binding.hiddenThingsLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)
//            binding.collateLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)

            val intent = Intent(this, WhatIsInImgActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicName)
            startActivity(intent)
        }

        binding.aliAndAyyaLayout.setOnClickListener {

            selectedTopicName = "AliAndAyya"
//            binding.whatisinImgLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)
//
//            binding.aliAndAyyaLayoutConstr.setBackgroundResource(R.drawable.round_back_white_stroke_20)
//            binding.hiddenThingsLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)
//            binding.collateLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)

            val intent = Intent(this, AliAndAyyaActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopicName)
            startActivity(intent)
        }

        binding.hiddenThingsLayout.setOnClickListener {

            selectedTopicName = "HiddenThings"
//            binding.whatisinImgLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)
//
//            binding.aliAndAyyaLayoutConstr.setBackgroundResource(R.drawable.game_inner_layout_style)
//            binding.hiddenThingsLayoutConst.setBackgroundResource(R.drawable.round_back_white_stroke_20)
//            binding.collateLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)

        }
        binding.collateLayout.setOnClickListener {

            selectedTopicName = "Collate"
//            binding.whatisinImgLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)
//
//            binding.aliAndAyyaLayoutConstr.setBackgroundResource(R.drawable.game_inner_layout_style)
//            binding.hiddenThingsLayoutConst.setBackgroundResource(R.drawable.game_inner_layout_style)
//            binding.collateLayoutConst.setBackgroundResource(R.drawable.round_back_white_stroke_20)

        }

//        binding.startGameButton.setOnClickListener {
//            if (selectedTopicName.isEmpty()){
//                Toast.makeText(this,"Please select the game", Toast.LENGTH_SHORT).show()
//            }
//            else{
//                when (selectedTopicName) {
//                    "AliAndAyya" -> {
//                        val intent = Intent(this, AliAndAyyaActivity::class.java)
//                        intent.putExtra("selectedTopic", selectedTopicName)
//                        startActivity(intent)
//                    }
//                    "HiddenThings" -> {
//                        Toast.makeText(this, "HiddenThings", Toast.LENGTH_SHORT).show()
//                    }
//                    "WhatIsInImg" -> {
//                        val intent = Intent(this, WhatIsInImgActivity::class.java)
//                        intent.putExtra("selectedTopic", selectedTopicName)
//                        startActivity(intent)
//                    }
//                    "Collate" -> {
//                        Toast.makeText(this, "Collate", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//        }


    }

    override fun onStart() {
        super.onStart()
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
            onBackPressed()
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
            Toast.makeText(this, "Bell clicked", Toast.LENGTH_SHORT).show()
        }
        binding.imExit.setOnClickListener {
            sharePref.edit().clear().apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.imGuitar.setOnClickListener {
            Toast.makeText(this, "Guitar clicked", Toast.LENGTH_SHORT).show()
        }
        binding.imSound.setOnClickListener {
            Toast.makeText(this, "Sound clicked", Toast.LENGTH_SHORT).show()
        }
    }

}