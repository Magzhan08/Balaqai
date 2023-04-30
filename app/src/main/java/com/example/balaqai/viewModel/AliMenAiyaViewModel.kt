package com.example.balaqai.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.Traditions
import com.example.balaqai.game.data.gameData
import com.example.balaqai.repository.AliMenAiyaRepositoryImpl
import com.example.balaqai.repository.gameRepository

class AliMenAiyaViewModel: ViewModel() {
    private val _gameAliMenAiyaList: MutableLiveData<List<gameData>?> = MutableLiveData(null)
    private val repository: gameRepository = AliMenAiyaRepositoryImpl()

    private val callback: gameRepository.Callback = object : gameRepository.Callback{
        override fun onGameDataLoaded(gameDataList: List<gameData>?) {
            _gameAliMenAiyaList.value = gameDataList
        }
    }

    val gameAliMenAiyaList: LiveData<List<gameData>?> = _gameAliMenAiyaList

    fun gameOfAliMenAiya(){
        repository.getGameData(callback)
    }
}