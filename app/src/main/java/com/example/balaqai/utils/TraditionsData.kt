package com.example.balaqai.utils

import com.example.balaqai.Api.BalaqaiApi
import com.example.balaqai.data.Traditions

object TraditionsData {

    var traditionsOfRaisingADowryAll: MutableList<Traditions> = mutableListOf()
    var traditionsOfFamily: MutableList<Traditions> = mutableListOf()
    var trCustomsOfEducation: MutableList<Traditions> = mutableListOf()
    var trNauryzTraditions: MutableList<Traditions> = mutableListOf()
    var traditionOfIslam: MutableList<Traditions> = mutableListOf()
    var imageUrl: String = ""

    var selectedTraditionList: MutableList<Traditions> = mutableListOf()

    fun getTraditionsOfRaisingADowry(list: List<Traditions>){
        traditionsOfRaisingADowryAll = list as MutableList<Traditions>
    }
    fun getTraditionsOfFamily(list: List<Traditions>){
        traditionsOfFamily = list as MutableList<Traditions>
    }

    fun getTraditionsOfCustomsOfEducation(list: List<Traditions>){
        trCustomsOfEducation = list as MutableList<Traditions>
    }

    fun getNauryzTraditions(list: List<Traditions>){
        trNauryzTraditions = list as MutableList<Traditions>
    }
    fun getIslamTraditions(list: List<Traditions>){
        traditionOfIslam = list as MutableList<Traditions>
    }

    fun getTraditionsGroup(name: String){
        when(name){
            "Тәрбие салт-дәстүрлері" -> selectedTraditionList = trCustomsOfEducation
            "Отау Көтеру дәстүрлері" -> selectedTraditionList = traditionsOfRaisingADowryAll
            "Отбасы дәстүрлері" -> selectedTraditionList = traditionsOfFamily
            "Наурыз дәстүрлері" -> selectedTraditionList = trNauryzTraditions
            "Ислам дәстүрлері" -> selectedTraditionList = traditionOfIslam
        }
    }
    fun getTraditionImageUrl(name: String){
        when(name){
            "Тәрбие салт-дәстүрлері" -> imageUrl = BalaqaiApi.BASE_traditionsAndCustomersImage_URL
            "Отау Көтеру дәстүрлері" -> imageUrl = BalaqaiApi.BASE_traditionsOfRaisingImage_URL
            "Отбасы дәстүрлері" -> imageUrl = BalaqaiApi.BASE_familyImage_URL
            "Наурыз дәстүрлері" -> imageUrl = BalaqaiApi.BASE_nauryzImage_URL
            "Ислам дәстүрлері" -> imageUrl = BalaqaiApi.BASE_islamImage_URL
        }
    }
}