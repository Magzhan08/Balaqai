package com.example.balaqai.traditions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import com.example.balaqai.R
import com.example.balaqai.databinding.ActivityInfoTrBinding
import com.example.balaqai.traditions.adapters.ViewPagerAdapter
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


        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.buttonNext.setOnClickListener {
            val intent = VideoTrActivity.newIntent(this@InfoTrActivity,"SOMETHING")
            startActivity(intent)
        }

       initVpAdapter()

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

        fun newIntent(context: Context, nameTradition: String): Intent {
            val intent = Intent(context,InfoTrActivity::class.java)
            intent.putExtra(EXTRA_NAME_OF_TRADITION,nameTradition)
            return intent
        }
    }
}