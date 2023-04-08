package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.Traditions
import com.example.balaqai.repository.FamilyTraditionRepositoryImpl
import com.example.balaqai.repository.TraditionRepository

class FamilyTraditionViewModel: ViewModel() {
    private val _trFamilyList: MutableLiveData<List<Traditions>?> = MutableLiveData(null)
    private val repository: TraditionRepository = FamilyTraditionRepositoryImpl()

    private val callback: TraditionRepository.Callback = object : TraditionRepository.Callback{
        override fun onTraditionLoaded(traditionsList: List<Traditions>?) {
            _trFamilyList.value = traditionsList
        }
    }

    val trFamilyList: LiveData<List<Traditions>?> = _trFamilyList

    fun traditionOfFamily(){
        repository.getTradition(callback)
    }
}