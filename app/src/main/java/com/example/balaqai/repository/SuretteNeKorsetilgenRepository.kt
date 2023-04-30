package com.example.balaqai.repository

import com.example.balaqai.game.data.SuretteNeKorsetilgenData

interface SuretteNeKorsetilgenRepository {

    fun getSuretteNeKorsetilgen(callback: Callback)

    interface Callback{
        fun onSuretteNeKorsetilgenLoaded(suretteNeKorsetilgenList: List<SuretteNeKorsetilgenData>?)
    }
}