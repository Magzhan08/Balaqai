package com.example.balaqai.traditions


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.R
import com.example.balaqai.authorization.MainActivity
import com.example.balaqai.databinding.ActivityTraditionsBinding
import com.example.balaqai.game.GamesActivity
import com.example.balaqai.traditions.adapters.TraditionsGroupAdapter
import com.example.balaqai.traditions.data.Tradition
import com.example.balaqai.utils.SharedPref
import com.example.balaqai.utils.TraditionsData
import com.example.balaqai.viewModel.*
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*

class TraditionsActivity : AppCompatActivity(), TraditionsGroupAdapter.Listener {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var binding: ActivityTraditionsBinding
    private val adapter = TraditionsGroupAdapter(this, this)
    private val tradList = ArrayList<Tradition>()
    private lateinit var sharePref: SharedPreferences

    private lateinit var scope: CoroutineScope


    val traditionsOfRaisingADowryViewModel: TraditionsOfRaisingADowryViewModel by viewModels()
    val traditionOfFamilyViewModel: FamilyTraditionViewModel by viewModels()
    val trCustomsOfEducationViewModel: TraditionsAndCustomsOfEducationViewModel by viewModels()
    val trNauryzTraditionsViewModel: NauryzTraditionsViewModel by viewModels()
    val traditionOfIslamViewModel: IslamTraditionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTraditionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = Firebase.analytics

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "TraditionsActivity")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "TraditionsActivity OPEN")

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        traditionsOfRaisingADowryViewModel.traditionsOfRaisingADowry()
        traditionOfFamilyViewModel.traditionOfFamily()
        trCustomsOfEducationViewModel.trCustomsOfEducation()
        trNauryzTraditionsViewModel.trNauryzTraditions()
        traditionOfIslamViewModel.traditionOfIslam()

        scope = CoroutineScope(Dispatchers.Main)
        scope.launch {
            val settingsItemsTolist = withContext(Dispatchers.Main) {
                async {

                    traditionsOfRaisingADowryViewModel.trOfRaisingList.observe(this@TraditionsActivity) { traditionsOfRaisingADowry ->
                        if (traditionsOfRaisingADowry != null) {
                            TraditionsData.getTraditionsOfRaisingADowry(traditionsOfRaisingADowry)
                        }
                    }

                    traditionOfFamilyViewModel.trFamilyList.observe(this@TraditionsActivity) { traditionOfFamily ->
                        if (traditionOfFamily != null) {
                            TraditionsData.getTraditionsOfFamily(traditionOfFamily)
                        }
                    }

                    trCustomsOfEducationViewModel.trCustomsOfEducationList.observe(this@TraditionsActivity) { trCustomsOfEducation ->
                        if (trCustomsOfEducation != null) {
                            TraditionsData.getTraditionsOfCustomsOfEducation(trCustomsOfEducation)
                        }
                    }

                    trNauryzTraditionsViewModel.trNauryzTraditionsList.observe(this@TraditionsActivity) { trNauryzTraditions ->
                        if (trNauryzTraditions != null) {
                            TraditionsData.getNauryzTraditions(trNauryzTraditions)
                        }
                    }

                    traditionOfIslamViewModel.trIslamList.observe(this@TraditionsActivity) { traditionOfIslam ->
                        if (traditionOfIslam != null) {
                            TraditionsData.getIslamTraditions(traditionOfIslam)
                        }
                    }

                }
            }
            //settingsItemsTolist.start()
            settingsItemsTolist.await()
            delay(500L)
            adapter.setData(myTraditions())

        }

        initRcView()
        selectedSettings()
    }

    override fun onStart() {
        super.onStart()

        // setTradition()
        // Log.d("MyTag","traditionsOfRaisingADowryAll[0].name2 :  ${traditionsOfRaisingADowryAll[0].name}")


        sharePref = getSharedPreferences("USER_PREF", Context.MODE_PRIVATE)

        //firebaseAnalytics.setUserProperty("user_age", SharedPref.getUserAge(sharePref))
        //val user: User = intent.getStringArrayExtra("userData")
        binding.userName.text = SharedPref.getUserName(sharePref)
        binding.btnOpenProfile.setOnClickListener {

            ProfileAndSettings.showProfileDialog(
                this,
                SharedPref.getUserName(sharePref),
                SharedPref.getUserAge(sharePref),
                SharedPref.getUserEmail(sharePref)

            )
        }
        binding.settings.setOnClickListener {
            if (binding.settingsPicker.isShown) {
                closeSettings()
            } else {
                openSettings()
            }
        }

        binding.btnGames.setOnClickListener {
            val intent = Intent(this, GamesActivity::class.java)
            startActivity(intent)

        }


    }


    private fun initRcView() = with(binding) {
        rcViewTr.layoutManager =
            LinearLayoutManager(this@TraditionsActivity, LinearLayoutManager.VERTICAL, false)
        rcViewTr.adapter = adapter
    }

    private fun openSettings() {
        binding.settingsPicker.visibility = View.VISIBLE
        val openAnim = AnimationUtils.loadAnimation(this, R.anim.open_settings_picker)
        binding.settingsPicker.startAnimation(openAnim)
    }

    private fun closeSettings() {
        val openAnim = AnimationUtils.loadAnimation(this, R.anim.close_settings_picker)
        openAnim.setAnimationListener(object : Animation.AnimationListener {
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

    private fun selectedSettings() {
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

    private fun myTraditions(): ArrayList<Tradition> {

        val trad1 = Tradition(
            "Тәрбие салт-дәстүрлері",
            "${BalaqaiApi.BASE_traditionsAndCustomersImage_URL}${TraditionsData.trCustomsOfEducation[2].image}",
            "${BalaqaiApi.BASE_traditionsAndCustomersImage_URL}${TraditionsData.trCustomsOfEducation[1].image}",
            TraditionsData.trCustomsOfEducation[2].name,
            TraditionsData.trCustomsOfEducation[1].name
        )

        tradList.add(trad1)
        val trad2 = Tradition(
            "Отау Көтеру дәстүрлері",
            "${BalaqaiApi.BASE_traditionsOfRaisingImage_URL}${TraditionsData.traditionsOfRaisingADowryAll[0].image}",
            "${BalaqaiApi.BASE_traditionsOfRaisingImage_URL}${TraditionsData.traditionsOfRaisingADowryAll[1].image}",
            TraditionsData.traditionsOfRaisingADowryAll[0].name,
            TraditionsData.traditionsOfRaisingADowryAll[1].name
        )
        tradList.add(trad2)
        val trad3 = Tradition(
            "Отбасы дәстүрлері",
            "${BalaqaiApi.BASE_familyImage_URL}${TraditionsData.traditionsOfFamily[0].image}",
            "${BalaqaiApi.BASE_familyImage_URL}${TraditionsData.traditionsOfFamily[1].image}",
            TraditionsData.traditionsOfFamily[0].name,
            TraditionsData.traditionsOfFamily[1].name
        )
        tradList.add(trad3)
        val trad4 = Tradition(
            "Наурыз дәстүрлері",
            "${BalaqaiApi.BASE_nauryzImage_URL}${TraditionsData.trNauryzTraditions[2].image}",
            "${BalaqaiApi.BASE_nauryzImage_URL}${TraditionsData.trNauryzTraditions[0].image}",
            TraditionsData.trNauryzTraditions[2].name,
            TraditionsData.trNauryzTraditions[0].name
        )
        tradList.add(trad4)
        val trad5 = Tradition(
            "Ислам дәстүрлері",
            "${BalaqaiApi.BASE_islamImage_URL}${TraditionsData.traditionOfIslam[0].image}",
            "${BalaqaiApi.BASE_islamImage_URL}${TraditionsData.traditionOfIslam[1].image}",
            TraditionsData.traditionOfIslam[0].name,
            TraditionsData.traditionOfIslam[1].name
        )
        tradList.add(trad5)
        return tradList
    }


    override fun onClickRight(name: String?, tradition: Tradition) {
        Toast.makeText(this, "Clicked on $name", Toast.LENGTH_SHORT).show()

        val params = Bundle()
        params.putString("tradition_name", name)
        firebaseAnalytics.logEvent("open_tradition", params)
        TraditionsData.getTraditionImageUrl(tradition.title)
        val intent = TraditionActivity.newIntent(this@TraditionsActivity, "$name", tradition.title)
        startActivity(intent)
    }


    override fun onClickLeft(name: String?, tradition: Tradition) {
        val params = Bundle()
        params.putString("tradition_name", name)
        firebaseAnalytics.logEvent("open_tradition", params)
        TraditionsData.getTraditionImageUrl(tradition.title)
        Toast.makeText(this, "Clicked on $name", Toast.LENGTH_SHORT).show()
        val intent = TraditionActivity.newIntent(this@TraditionsActivity, "$name", tradition.title)
        startActivity(intent)
    }

    override fun onClickNext(tradition: Tradition) {
        val params = Bundle()
        params.putString("tradition_group_name", tradition.title)
        firebaseAnalytics.logEvent("open_tradition_group", params)
        TraditionsData.getTraditionImageUrl(tradition.title)
        Toast.makeText(this, "Clicked on ${tradition.title}", Toast.LENGTH_SHORT).show()
        val intent = SelectedGroupTrActivity.newIntent(this@TraditionsActivity, tradition.title)
        startActivity(intent)
    }


}