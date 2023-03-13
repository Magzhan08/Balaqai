package com.example.balaqai.traditions


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balaqai.R
import com.example.balaqai.authorization.MainActivity
import com.example.balaqai.data.TraditionsOfRaisingADowry
import com.example.balaqai.databinding.ActivityTraditionsBinding
import com.example.balaqai.game.GamesActivity
import com.example.balaqai.traditions.adapters.TraditionsGroupAdapter
import com.example.balaqai.traditions.data.Tradition
import com.example.balaqai.utils.SharedPref
import com.example.balaqai.utils.Traditions
import com.example.balaqai.viewModel.TraditionsOfRaisingADowryViewModel

class TraditionsActivity : AppCompatActivity(),TraditionsGroupAdapter.Listener {
    private lateinit var binding: ActivityTraditionsBinding
    private val adapter = TraditionsGroupAdapter(this)
    private val tradList = ArrayList<Tradition>()
    private lateinit var sharePref: SharedPreferences
    private var traditionsOfRaisingADowryAll: MutableList<TraditionsOfRaisingADowry> = mutableListOf()

    val traditionsOfRaisingADowryViewModel: TraditionsOfRaisingADowryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        traditionsOfRaisingADowryViewModel.traditionsOfRaisingADowry()

        traditionsOfRaisingADowryViewModel.trOfRaisingList.observe(this){traditionsOfRaisingADowry ->
            if (traditionsOfRaisingADowry != null) {
                traditionsOfRaisingADowryAll = traditionsOfRaisingADowry as MutableList<TraditionsOfRaisingADowry>
                Log.d("MyTag","traditionsOfRaisingADowryAll[0].name1 :  ${traditionsOfRaisingADowryAll[0].name}")
                Log.d("MyTag","traditionsOfRaisingADowryAll :  $traditionsOfRaisingADowryAll")
            }
        }


        initRcView()
        selectedSettings()
    }

    override fun onStart() {
        super.onStart()

       // setTradition()
       // Log.d("MyTag","traditionsOfRaisingADowryAll[0].name2 :  ${traditionsOfRaisingADowryAll[0].name}")


        sharePref = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)




        //val user: User = intent.getStringArrayExtra("userData")
        binding.userName.text = SharedPref.getUserName(sharePref)
        binding.btnOpenProfile.setOnClickListener {

            ProfileAndSettings.showProfileDialog(this,SharedPref.getUserName(sharePref),SharedPref.getUserAge(sharePref), SharedPref.getUserEmail(sharePref))
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


    }





    private fun initRcView() = with(binding){
        rcViewTr.layoutManager = LinearLayoutManager(this@TraditionsActivity,LinearLayoutManager.VERTICAL,false)
        rcViewTr.adapter = adapter
        adapter.setData(myTraditions())
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

    private fun myTraditions(): ArrayList<Tradition>{

        val trad1 = Tradition("Тәрбие салт-дәстүрлері",R.drawable.shildehana,R.drawable.tysaukeser,"Шілдехана","Тұсаукесер")
        tradList.add(trad1)
//        val trad2 = Tradition("Отау Көтеру дәстүрлері",R.drawable.sirgasaly,R.drawable.kiyt,traditionsOfRaisingADowryAll[0].name,traditionsOfRaisingADowryAll[1].name)
//        Log.d("MyTag","traditionsOfRaisingADowryAll[0].name2 :  ${traditionsOfRaisingADowryAll[0].name}")
//        tradList.add(trad2)
        val trad3 = Tradition("Отбасы дәстүрлері",R.drawable.ayiztiy,R.drawable.bata,"Ауыз тию","Бата")
        tradList.add(trad3)
        val trad4 = Tradition("Наурыз дәстүрлері",R.drawable.nayruztoi,R.drawable.saumalyk,"Наурыз той","Саумалық")
        tradList.add(trad4)
        val trad5 = Tradition("Ислам дәстүрлері",R.drawable.ait,R.drawable.zharapazan,"Айт","Жарапазан")
        tradList.add(trad5)
        val trad6 = Tradition("Test дәстүрлері",R.drawable.test1img,R.drawable.test2hpegimg,"Test1","Test2")
        tradList.add(trad6)
        val trad7 = Tradition("Test дәстүрлері",R.drawable.test3,R.drawable.test4,"Test3","Test4")
        tradList.add(trad7)
        return tradList
    }


    override fun onClickRight(name: String?) {
        Toast.makeText(this, "Clicked on $name", Toast.LENGTH_SHORT).show()
        val intent = TraditionActivity.newIntent(this@TraditionsActivity,"$name")
        startActivity(intent)
    }


    override fun onClickLeft(name: String?) {
        Toast.makeText(this, "Clicked on $name", Toast.LENGTH_SHORT).show()
        val intent = TraditionActivity.newIntent(this@TraditionsActivity,"$name")
        startActivity(intent)
    }

    override fun onClickNext(tradition: Tradition) {
        Toast.makeText(this, "Clicked on ${tradition.title}", Toast.LENGTH_SHORT).show()
        val intent = SelectedGroupTrActivity.newIntent(this@TraditionsActivity,"${tradition.title}")
        startActivity(intent)
    }



}