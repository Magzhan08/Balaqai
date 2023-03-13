package com.example.balaqai.viewModel

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.balaqai.data.User
import com.example.balaqai.repository.UserRepository
import com.example.balaqai.repository.UserRepositoryImpl

class UserViewModel : ViewModel() {
    private val _currentUser: MutableLiveData<User?> = MutableLiveData(null)
    private val repository: UserRepository = UserRepositoryImpl()

    private val callback: UserRepository.Callback = object : UserRepository.Callback {
        override fun onUserLoaded(user: User?) {
            _currentUser.value = user
        }
    }

    val currentUser: LiveData<User?> = _currentUser



    fun loadUser(user_email: String) {
        repository.getUserDetails(user_email, callback)
    }
}