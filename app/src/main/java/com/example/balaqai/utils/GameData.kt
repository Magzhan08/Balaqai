package com.example.balaqai.utils

import com.example.balaqai.game.data.SuretteNeKorsetilgenData
import com.example.balaqai.game.data.gameData

object GameData {
    var gameOfAliMenAiya: MutableList<gameData> = mutableListOf()
    var gameSuretteNeKorsetilgen: MutableList<SuretteNeKorsetilgenData> = mutableListOf()

    fun getGameOfAliMenAiya(list: List<gameData>){
        gameOfAliMenAiya = list as MutableList<gameData>
    }

    fun getSuretteNeKorsetilgen(list: List<SuretteNeKorsetilgenData>){
        gameSuretteNeKorsetilgen = list as MutableList<SuretteNeKorsetilgenData>
    }

}