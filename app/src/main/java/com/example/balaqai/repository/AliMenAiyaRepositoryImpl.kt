package com.example.balaqai.repository

import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.game.data.gameData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AliMenAiyaRepositoryImpl: gameRepository {
    override fun getGameData(callback: gameRepository.Callback) {
        BalaqaiApi.INSTANCE.getAllAliMenAiya().enqueue(object : Callback<List<gameData>>{
            override fun onResponse(
                call: Call<List<gameData>>,
                response: Response<List<gameData>>
            ) {
                if (response.isSuccessful){
                    callback.onGameDataLoaded(response.body())
                } else{
                    callback.onGameDataLoaded(null)
                }
            }

            override fun onFailure(call: Call<List<gameData>>, t: Throwable) {
                callback.onGameDataLoaded(null)
            }

        })
    }
}