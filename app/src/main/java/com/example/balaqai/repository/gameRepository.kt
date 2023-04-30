package com.example.balaqai.repository

import com.example.balaqai.game.data.gameData

interface gameRepository {

    fun getGameData(callback: Callback)

    interface Callback{
        fun onGameDataLoaded(gameDataList: List<gameData>?)
    }
}