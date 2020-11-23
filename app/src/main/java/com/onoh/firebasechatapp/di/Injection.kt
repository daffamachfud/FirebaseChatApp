package com.onoh.firebasechatapp.di

import com.onoh.firebasechatapp.data.AppRepository

object Injection {
    fun provideRepository(): AppRepository {
        return AppRepository.getInstance()
    }
}