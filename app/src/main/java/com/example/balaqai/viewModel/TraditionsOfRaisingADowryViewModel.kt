package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.Traditions
import com.example.balaqai.repository.TraditionsOfRaisingADowryRepository
import com.example.balaqai.repository.TraditionsOfRaisingADowryRepositoryImpl

class TraditionsOfRaisingADowryViewModel: ViewModel() {
    private val _trOfRaisingList: MutableLiveData<List<Traditions>?> = MutableLiveData(null)
    private val repository: TraditionsOfRaisingADowryRepository = TraditionsOfRaisingADowryRepositoryImpl()

    private val callback: TraditionsOfRaisingADowryRepository.Callback = object : TraditionsOfRaisingADowryRepository.Callback{
        override fun onTraditionsOfRaisingADowryLoaded(traditionsOfRaisingADowryList: List<Traditions>?) {
            _trOfRaisingList.value = traditionsOfRaisingADowryList
        }
    }

    val trOfRaisingList: LiveData<List<Traditions>?> = _trOfRaisingList



    fun traditionsOfRaisingADowry(){
        repository.getRaisingADowryTraditionsDetails(callback)
    }
}