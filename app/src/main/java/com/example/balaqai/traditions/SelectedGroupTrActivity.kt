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
import com.example.balaqai.traditions.adapters.SelectedGrTrAdapter
import com.example.balaqai.traditions.data.SelectedGroupTr
import com.example.balaqai.utils.SharedPref

class SelectedGroupTrActivity : AppCompatActivity(), SelectedGrTrAdapter.Listener {
    private lateinit var binding: ActivitySelectedGroupTrBinding
    private val adapter = SelectedGrTrAdapter(this)
    private lateinit var sharePref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedGroupTrBinding.inflate(layoutInflater)
        setContentView(binding.root)
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



        if (!intent.hasExtra(EXTRA_NAME_TRADITION_GROUP)){
            finish()
            return
        }
        val nameOfTrGroup = intent.getStringExtra(EXTRA_NAME_TRADITION_GROUP)

        binding.tvNameOfTrGroup.text = nameOfTrGroup
    }
    private fun initRcViewSelect() = with(binding){
        rcViewSelectedTr.layoutManager = GridLayoutManager(this@SelectedGroupTrActivity, 3)
        rcViewSelectedTr.adapter = adapter
        adapter.setTraditions(myTestSelectedTraditions())
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


    private fun myTestSelectedTraditions(): ArrayList<SelectedGroupTr>{
        val tradList = ArrayList<SelectedGroupTr>()

        val trad1 = SelectedGroupTr(R.drawable.shildehana,"Шілдехана")
        tradList.add(trad1)
        val trad2 = SelectedGroupTr(R.drawable.kiyt,"Киіт")
        tradList.add(trad2)
        val trad3 = SelectedGroupTr(R.drawable.bata,"Бата")
        tradList.add(trad3)
        val trad4 = SelectedGroupTr(R.drawable.saumalyk,"Саумалық")
        tradList.add(trad4)
        val trad5 = SelectedGroupTr(R.drawable.zharapazan,"Жарапазан")
        tradList.add(trad5)
        val trad6 = SelectedGroupTr(R.drawable.tysaukeser,"Тұсаукесер")
        tradList.add(trad6)
        val trad7 = SelectedGroupTr(R.drawable.sirgasaly,"Сырға салу")
        tradList.add(trad7)
        val trad8 = SelectedGroupTr(R.drawable.ayiztiy,"Ауыз тию")
        tradList.add(trad8)
        val trad9 = SelectedGroupTr(R.drawable.test1img,"Айт")
        tradList.add(trad9)
        val trad10 = SelectedGroupTr(R.drawable.test2hpegimg,"Наурыз той")
        tradList.add(trad10)

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

    override fun onClick(name: String) {
        Toast.makeText(this, "Clicked on $name", Toast.LENGTH_SHORT).show()
        val intent = TraditionActivity.newIntent(this@SelectedGroupTrActivity,"$name")
        startActivity(intent)
    }
}