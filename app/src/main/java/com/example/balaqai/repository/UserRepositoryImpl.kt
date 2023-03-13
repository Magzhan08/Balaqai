package com.example.balaqai.repository

import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepositoryImpl: UserRepository {
    override fun getUserDetails(user_email: String, callback: UserRepository.Callback) {
        BalaqaiApi.INSTANCE.getUser(user_email).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    callback.onUserLoaded(response.body())
                } else{
                    callback.onUserLoaded(null)
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                callback.onUserLoaded(null)
            }

        })

    }

}