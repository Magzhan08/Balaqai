package com.example.balaqai.repository

import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.data.Traditions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TraditionsOfRaisingADowryRepositoryImpl: TraditionsOfRaisingADowryRepository {
    override fun getRaisingADowryTraditionsDetails(callback: TraditionsOfRaisingADowryRepository.Callback) {
        BalaqaiApi.INSTANCE.getAllTraditionsOfRaisingADowry().enqueue(object : Callback<List<Traditions>> {
            override fun onResponse(call: Call<List<Traditions>>, response: Response<List<Traditions>>) {
                if (response.isSuccessful){
                    callback.onTraditionsOfRaisingADowryLoaded(response.body())
                } else{
                    callback.onTraditionsOfRaisingADowryLoaded(null)
                }
            }

            override fun onFailure(call: Call<List<Traditions>>, t: Throwable) {
                callback.onTraditionsOfRaisingADowryLoaded(null)
            }

        })
    }

}