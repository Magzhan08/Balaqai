package com.example.balaqai.traditions

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.balaqai.R
import com.example.balaqai.authorization.MainActivity
import com.example.balaqai.databinding.ActivitySelectedGroupTrBinding
import com.example.balaqai.game.GamesActivity
import com.example.balaqai.traditions.adapters.SelectedGrTrAdapter
import com.example.balaqai.traditions.data.SelectedGroupTr
import com.example.balaqai.utils.SharedPref
import com.example.balaqai.utils.TraditionsData

class SelectedGroupTrActivity : AppCompatActivity(), SelectedGrTrAdapter.Listener {
    private lateinit var binding: ActivitySelectedGroupTrBinding
    private val adapter = SelectedGrTrAdapter(this,this)
    private lateinit var sharePref: SharedPreferences
    private lateinit var nameOfTrGroup: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedGroupTrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_NAME_TRADITION_GROUP)){
            finish()
            return
        }
        nameOfTrGroup = intent.getStringExtra(EXTRA_NAME_TRADITION_GROUP).toString()
        TraditionsData.getTraditionsGroup(nameOfTrGroup)
        binding.tvNameOfTrGroup.text = nameOfTrGroup

        initRcViewSelect()
        selectedSettings()


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

        binding.btnGames.setOnClickListener {
            val intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)
        }
        binding.btnTraditions.setOnClickListener {
            val intent = Intent(this, TraditionsActivity::class.java)
            startActivity(intent)
        }

    }
    private fun initRcViewSelect() = with(binding){
        rcViewSelectedTr.layoutManager = GridLayoutManager(this@SelectedGroupTrActivity, 3)
        rcViewSelectedTr.adapter = adapter
        adapter.setTraditions(mySelectedTraditions())
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


    private fun mySelectedTraditions(): ArrayList<SelectedGroupTr>{
        val tradList = ArrayList<SelectedGroupTr>()

        for (tradition in TraditionsData.selectedTraditionList){
            if(tradition.image.isNullOrEmpty()){
                    tradList.add(SelectedGroupTr("",tradition.name.replace("\n","")))
            }else {
                tradList.add(SelectedGroupTr(tradition.image, tradition.name.replace("\n", "")))
            }
        }

        return tradList
    }

    companion object{
        private const val EXTRA_NAME_TRADITION_GROUP = "nameOfTraditionGroup"

        fun newIntent(context: Context, nameTradition: String): Intent {
            val intent = Intent(context,SelectedGroupTrActivity::class.java)
            intent.putExtra(EXTRA_NAME_TRADITION_GROUP,nameTradition)
            return intent
        }
    }

    override fun onClick(name: String,) {
        Toast.makeText(this, "Clicked on $name", Toast.LENGTH_SHORT).show()
        val intent = TraditionActivity.newIntent(this@SelectedGroupTrActivity, name, nameOfTrGroup)
        startActivity(intent)
    }
}