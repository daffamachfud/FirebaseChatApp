package com.onoh.firebasechatapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onoh.firebasechatapp.data.local.User
import com.onoh.firebasechatapp.data.remote.RemoteDataSource

class AppRepository private constructor(private val remoteDataSource: RemoteDataSource) :AppDataSource{

    companion object{
        @Volatile
        private var instance:AppRepository?=null
        fun getInstance(remoteData: RemoteDataSource): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteData)
            }
    }

    override fun getListUser(): LiveData<List<User>> {
        val userResults = MutableLiveData<List<User>>()
        remoteDataSource.getListUser(object :RemoteDataSource.LoadUserCallback{
            override fun onAllUserReceived(userResult: List<User>) {
                val userList = ArrayList<User>()
                for (response in userResult){
                    val user = User(response.userId,response.username,response.profileImage)
                    userList.add(user)
                }
                userResults.postValue(userList)
            }
        })
        return userResults
    }


}