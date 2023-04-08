package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.Traditions
import com.example.balaqai.repository.TraditionRepository
import com.example.balaqai.repository.TraditionsAndCustomsOfEducationImpl
import retrofit2.Callback

class TraditionsAndCustomsOfEducationViewModel: ViewModel() {
    private val _trCustomsOfEducationList: MutableLiveData<List<Traditions>?> = MutableLiveData(null)
    private val repository: TraditionRepository = TraditionsAndCustomsOfEducationImpl()

    private val callback: TraditionRepository.Callback = object : TraditionRepository.Callback{
        override fun onTraditionLoaded(traditionsList: List<Traditions>?) {
            _trCustomsOfEducationList.value = traditionsList
        }
    }

    val trCustomsOfEducationList: LiveData<List<Traditions>?> = _trCustomsOfEducationList
    fun trCustomsOfEducation(){
        repository.getTradition(callback)
    }
}