package com.onoh.firebasechatapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.onoh.firebasechatapp.data.model.User


class AppRepository  :AppDataSource{

    companion object{
        @Volatile
        private var instance:AppRepository?=null
        fun getInstance(): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository()
            }
    }
    val userResults = MutableLiveData<List<User>>()

    override fun getListUser(): LiveData<List<User>> {
        val resultUsers = ArrayList<User>()
        val firebase = FirebaseAuth.getInstance().currentUser!!
        val databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnapshot: DataSnapshot in snapshot.children){
                    val user = dataSnapshot.getValue(User::class.java)
                    if(!user!!.userId.equals(firebase.uid)){
                        resultUsers.add(user)
                    }
                }
                userResults.postValue(resultUsers)
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("onResponse", error.message)
            }
        })
        return userResults
    }


}