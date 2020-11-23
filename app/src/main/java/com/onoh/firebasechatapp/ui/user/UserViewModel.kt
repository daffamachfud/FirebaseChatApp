package com.onoh.firebasechatapp.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.onoh.firebasechatapp.data.AppRepository
import com.onoh.firebasechatapp.data.model.User

class UserViewModel(private val appRepository: AppRepository):ViewModel() {
    fun getUserList():LiveData<List<User>> = appRepository.getListUser()
}