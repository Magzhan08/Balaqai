package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.Traditions
import com.example.balaqai.repository.IslamTraditionRepositoryImpl
import com.example.balaqai.repository.TraditionRepository

class IslamTraditionViewModel: ViewModel() {
    private val _trIslamList: MutableLiveData<List<Traditions>?> = MutableLiveData(null)
    private val repository: TraditionRepository = IslamTraditionRepositoryImpl()

    private val callback: TraditionRepository.Callback = object : TraditionRepository.Callback{
        override fun onTraditionLoaded(traditionsList: List<Traditions>?) {
            _trIslamList.value = traditionsList
        }
    }

    val trIslamList: LiveData<List<Traditions>?> = _trIslamList

    fun traditionOfIslam(){
        repository.getTradition(callback)
    }
}