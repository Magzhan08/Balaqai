package com.example.balaqai.repository

import com.example.balaqai.data.Traditions

interface TraditionsOfRaisingADowryRepository {
    fun getRaisingADowryTraditionsDetails(callback: Callback)

    interface Callback{
        fun onTraditionsOfRaisingADowryLoaded(traditionsOfRaisingADowryList: List<Traditions>?)
    }
}