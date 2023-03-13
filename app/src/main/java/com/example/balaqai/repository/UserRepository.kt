package com.example.balaqai.repository

import com.example.balaqai.data.User

interface UserRepository {
    fun getUserDetails(user_email: String, callback: Callback)

    interface Callback{
        fun onUserLoaded(user: User?)
    }
}