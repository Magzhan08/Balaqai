package com.example.balaqai.repository

import com.example.balaqai.data.TraditionsOfRaisingADowry

interface TraditionsOfRaisingADowryRepository {
    fun getRaisingADowryTraditionsDetails(callback: Callback)

    interface Callback{
        fun onTraditionsOfRaisingADowryLoaded(traditionsOfRaisingADowryList: List<TraditionsOfRaisingADowry>?)
    }
}