package com.example.balaqai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.balaqai.authorization.LoginFragment
import com.example.balaqai.utils.FragmentManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FragmentManager.setFragment(LoginFragment.newInstance(),this)
    }
    override fun onBackPressed() {
        if (FragmentManager.currentFragment is LoginFragment) super.onBackPressed()
        else FragmentManager.setFragment(LoginFragment.newInstance(), this)
    }
}