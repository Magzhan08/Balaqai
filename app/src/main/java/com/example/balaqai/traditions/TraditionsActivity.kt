package com.example.balaqai.traditions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.balaqai.R
import com.example.balaqai.utils.FragmentManager

class TraditionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traditions)
        FragmentManager.setFragment(TraditionsGroupsFragment.newInstance(), this)
    }
}