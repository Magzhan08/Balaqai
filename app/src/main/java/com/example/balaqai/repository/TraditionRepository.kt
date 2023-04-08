package com.example.balaqai.repository

import com.example.balaqai.data.Traditions

interface TraditionRepository {
    fun getTradition(callback: Callback)

    interface Callback{
        fun onTraditionLoaded(traditionsList: List<Traditions>?)
    }
}