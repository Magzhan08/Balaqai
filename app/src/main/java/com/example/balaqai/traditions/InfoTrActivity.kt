package com.example.balaqai.traditions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import com.example.balaqai.R
import com.example.balaqai.data.Traditions
import com.example.balaqai.databinding.ActivityInfoTrBinding
import com.example.balaqai.traditions.adapters.ViewPagerAdapter
import com.example.balaqai.traditions.data.Tradition
import com.example.balaqai.utils.TraditionsData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.roundToInt

class InfoTrActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoTrBinding
    private val fragList = listOf(
        MainInfoFragment.newInstance(),
        SecondInfoFragment.newInstance(),
        ThirdInfoFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoTrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_NAME_OF_TRADITION) || !intent.hasExtra(EXTRA_NAME_TRADITION_GROUP)){
            finish()
            return
        }
        val nameTradition = intent.getStringExtra(EXTRA_NAME_OF_TRADITION)
        val nameOfTrGroup = intent.getStringExtra(EXTRA_NAME_TRADITION_GROUP)
        if (nameOfTrGroup != null) {
            TraditionsData.getTraditionsGroup(nameOfTrGroup)
        }


        for (tradition in TraditionsData.selectedTraditionList){
            if (tradition.name == nameTradition){
                selectedTraditionInfo = tradition
            }
        }



        binding.buttonNext.setOnClickListener {
            val intent = VideoTrActivity.newIntent(this@InfoTrActivity,nameTradition.toString(),nameOfTrGroup.toString())
            startActivity(intent)
        }
        binding.beineBtn.setOnClickListener {
            val intent = VideoTrActivity.newIntent(this@InfoTrActivity,nameTradition.toString(),nameOfTrGroup.toString())
            startActivity(intent)
        }
        binding.quesAnsBtn.setOnClickListener {
            val intent = QuestionActivity.newIntent(this@InfoTrActivity,nameTradition.toString())
            startActivity(intent)
        }

            initVpAdapter()



        binding.btnBack.setOnClickListener {
            onBackPressed()
            finish()
        }

    }


    private fun  initVpAdapter(){
        val adapter = ViewPagerAdapter(this@InfoTrActivity,fragList)
        binding.viewPagerFragments.adapter = adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPagerFragments){
                tab, pos ->
        }.attach()

        for(i in fragList.indices){
            val textView = LayoutInflater.from(this@InfoTrActivity).inflate(R.layout.tab_title,null ) as TextView
            binding.tabLayout.getTabAt(i)?.customView = textView

        }

    }

    companion object{
        private const val EXTRA_NAME_OF_TRADITION = "nameTrad"
        private const val EXTRA_NAME_TRADITION_GROUP = "nameOfTraditionGroup"

        lateinit var selectedTraditionInfo: Traditions

        fun newIntent(context: Context, nameTradition: String,nameTraditionGroup: String): Intent {
            val intent = Intent(context,InfoTrActivity::class.java)
            intent.putExtra(EXTRA_NAME_OF_TRADITION,nameTradition)
            intent.putExtra(EXTRA_NAME_TRADITION_GROUP,nameTraditionGroup)
            return intent
        }
    }
}