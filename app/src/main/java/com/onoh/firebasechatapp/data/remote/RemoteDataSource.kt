package com.onoh.firebasechatapp.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.onoh.firebasechatapp.data.local.User

class RemoteDataSource {

    companion object{
        @Volatile
        private var instance:RemoteDataSource? = null
        fun getInstance():RemoteDataSource = instance?: synchronized(this){
            instance?: RemoteDataSource()
        }
    }

    fun getListUser(callback:LoadUserCallback){
        val resultUsers = ArrayList<User>()
        val firebase:FirebaseUser = FirebaseAuth.getInstance().currentUser!!
        val databaseReference:DatabaseReference = FirebaseDatabase.getInstance().getReference("Users")

        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnapshot:DataSnapshot in snapshot.children){
                    val user = dataSnapshot.getValue(User::class.java)
                    if(!user?.userId?.equals(firebase.uid)!!){
                        resultUsers.add(user)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.d("onResponse", error.message)
            }

        })
        callback.onAllUserReceived(resultUsers)
    }

    interface LoadUserCallback{
        fun onAllUserReceived(userResult:List<User>)
    }
}