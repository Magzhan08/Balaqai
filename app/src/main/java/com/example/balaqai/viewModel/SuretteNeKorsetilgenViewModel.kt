package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.game.data.SuretteNeKorsetilgenData
import com.example.balaqai.repository.SuretteNeKorsetilgenRepository
import com.example.balaqai.repository.SuretteNeKorsetilgenRepositoryImpl

class SuretteNeKorsetilgenViewModel: ViewModel() {
    private val _dataList: MutableLiveData<List<SuretteNeKorsetilgenData>?> = MutableLiveData(null)
    private val repository: SuretteNeKorsetilgenRepository = SuretteNeKorsetilgenRepositoryImpl()

    private val callback: SuretteNeKorsetilgenRepository.Callback = object : SuretteNeKorsetilgenRepository.Callback{
        override fun onSuretteNeKorsetilgenLoaded(suretteNeKorsetilgenList: List<SuretteNeKorsetilgenData>?) {
            _dataList.value = suretteNeKorsetilgenList
        }
    }

    val dataList: LiveData<List<SuretteNeKorsetilgenData>?> = _dataList

    fun gameSuretteNeKorsetilgen(){
        repository.getSuretteNeKorsetilgen(callback)
    }

}