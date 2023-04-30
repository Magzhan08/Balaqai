package com.example.balaqai.repository

import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.game.data.SuretteNeKorsetilgenData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SuretteNeKorsetilgenRepositoryImpl: SuretteNeKorsetilgenRepository {
    override fun getSuretteNeKorsetilgen(callback: SuretteNeKorsetilgenRepository.Callback) {
        BalaqaiApi.INSTANCE.getAllSuretteNeKorsetilgen().enqueue(object: Callback<List<SuretteNeKorsetilgenData>>{
            override fun onResponse(
                call: Call<List<SuretteNeKorsetilgenData>>,
                response: Response<List<SuretteNeKorsetilgenData>>
            ) {
                if (response.isSuccessful){
                    callback.onSuretteNeKorsetilgenLoaded(response.body())
                } else{
                    callback.onSuretteNeKorsetilgenLoaded(null)
                }
            }

            override fun onFailure(call: Call<List<SuretteNeKorsetilgenData>>, t: Throwable) {
                callback.onSuretteNeKorsetilgenLoaded(null)
            }

        })
    }
}