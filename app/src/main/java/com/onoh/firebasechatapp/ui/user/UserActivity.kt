package com.onoh.firebasechatapp.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.onoh.firebasechatapp.R
import com.onoh.firebasechatapp.vo.ViewModelFactory
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(this,factory)[UserViewModel::class.java]

        val userAdapter = UserAdapter()
        loading_user.visibility = View.VISIBLE
        viewModel.getUserList().observe(this,{
            if(it!= null){
                loading_user.visibility = View.GONE
                userAdapter.setUser(it)
                userAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"ERROR",Toast.LENGTH_SHORT).show()
            }
        })
        with(rv_user){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }


    }


}
