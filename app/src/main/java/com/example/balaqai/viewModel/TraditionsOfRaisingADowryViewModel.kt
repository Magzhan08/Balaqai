package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.TraditionsOfRaisingADowry
import com.example.balaqai.repository.TraditionsOfRaisingADowryRepository
import com.example.balaqai.repository.TraditionsOfRaisingADowryRepositoryImpl
import retrofit2.Callback

class TraditionsOfRaisingADowryViewModel: ViewModel() {
    private val _trOfRaisingList: MutableLiveData<List<TraditionsOfRaisingADowry>?> = MutableLiveData(null)
    private val repository: TraditionsOfRaisingADowryRepository = TraditionsOfRaisingADowryRepositoryImpl()

    private val callback: TraditionsOfRaisingADowryRepository.Callback = object : TraditionsOfRaisingADowryRepository.Callback{
        override fun onTraditionsOfRaisingADowryLoaded(traditionsOfRaisingADowryList: List<TraditionsOfRaisingADowry>?) {
            _trOfRaisingList.value = traditionsOfRaisingADowryList
        }
    }

    val trOfRaisingList: LiveData<List<TraditionsOfRaisingADowry>?> = _trOfRaisingList



    fun traditionsOfRaisingADowry(){
        repository.getRaisingADowryTraditionsDetails(callback)
    }
}