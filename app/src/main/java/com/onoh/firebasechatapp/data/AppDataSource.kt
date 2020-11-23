package com.onoh.firebasechatapp.data

import androidx.lifecycle.LiveData
import com.onoh.firebasechatapp.data.model.User

interface AppDataSource {

    fun getListUser(): LiveData<List<User>>
}