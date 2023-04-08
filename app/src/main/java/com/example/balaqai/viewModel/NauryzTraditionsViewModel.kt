package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.Traditions
import com.example.balaqai.repository.NauryzTraditionsRepositoryImpl
import com.example.balaqai.repository.TraditionRepository

class NauryzTraditionsViewModel: ViewModel() {
    private val _trNauryzTraditionsList: MutableLiveData<List<Traditions>?> = MutableLiveData(null)
    private val repository: TraditionRepository = NauryzTraditionsRepositoryImpl()

    private val callback: TraditionRepository.Callback = object : TraditionRepository.Callback{
        override fun onTraditionLoaded(traditionsList: List<Traditions>?) {
            _trNauryzTraditionsList.value = traditionsList
        }
    }

    val trNauryzTraditionsList: LiveData<List<Traditions>?> = _trNauryzTraditionsList

    fun trNauryzTraditions(){
        repository.getTradition(callback)
    }
}