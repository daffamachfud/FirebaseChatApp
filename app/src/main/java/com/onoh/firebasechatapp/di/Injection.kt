package com.onoh.firebasechatapp.di

import android.content.Context
import com.onoh.firebasechatapp.data.AppRepository
import com.onoh.firebasechatapp.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(): AppRepository {

        val remoteDataSource = RemoteDataSource.getInstance()

        return AppRepository.getInstance(remoteDataSource)
    }
}