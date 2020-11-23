package com.onoh.firebasechatapp.vo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.onoh.firebasechatapp.data.AppRepository
import com.onoh.firebasechatapp.di.Injection
import com.onoh.firebasechatapp.ui.user.UserViewModel

class ViewModelFactory private constructor(private val mAppRepository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object{
        @Volatile
        private var instance:ViewModelFactory? = null
        fun getInstance():ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UserViewModel::class.java) -> {
                UserViewModel(mAppRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}