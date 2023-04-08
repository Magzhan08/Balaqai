package com.example.balaqai.repository

import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.data.Traditions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IslamTraditionRepositoryImpl: TraditionRepository {
    override fun getTradition(callback: TraditionRepository.Callback) {
        BalaqaiApi.INSTANCE.getAllIslamTraditions().enqueue(object : Callback<List<Traditions>>{
            override fun onResponse(
                call: Call<List<Traditions>>,
                response: Response<List<Traditions>>
            ) {
                if (response.isSuccessful){
                    callback.onTraditionLoaded(response.body())
                } else {
                    callback.onTraditionLoaded(null)
                }
            }

            override fun onFailure(call: Call<List<Traditions>>, t: Throwable) {
                callback.onTraditionLoaded(null)
            }

        })
    }
}