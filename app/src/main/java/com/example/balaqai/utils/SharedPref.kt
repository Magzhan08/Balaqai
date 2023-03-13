package com.example.balaqai.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPref {
    const val USERNAME = "USERNAME"
    const val USEREMAIL = "USEREMAIL"
    const val USERAGE = "USERAGE"

    fun saveUserInfo(name: String, email: String, age: String ,sharedPreferences: SharedPreferences){
        val editor = sharedPreferences.edit()
        editor.putString(USERNAME, name)
        editor.putString(USEREMAIL, email)
        editor.putString(USERAGE, age)
        editor.apply()
    }

    fun getUserName(sharedPreferences: SharedPreferences) : String{
        return sharedPreferences.getString(USERNAME,"").toString()
    }
    fun getUserEmail(sharedPreferences: SharedPreferences) : String{
        return sharedPreferences.getString(USEREMAIL,"").toString()
    }
    fun getUserAge(sharedPreferences: SharedPreferences) : String{
        return sharedPreferences.getString(USERAGE,"").toString()
    }

}