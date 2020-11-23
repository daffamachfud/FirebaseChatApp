package com.onoh.firebasechatapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.onoh.firebasechatapp.ui.MainActivity
import com.onoh.firebasechatapp.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    private lateinit var databaseReference:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //inisiasi firebase auth
        auth = FirebaseAuth.getInstance()

        btn_register_submit.setOnClickListener{
            val userName = et_register_fullname.text.toString()
            val email = et_register_email.text.toString()
            val password = et_register_password.text.toString()
            val confirmPass = et_register_confirm_password.text.toString()
            if(userName.isEmpty()){
                et_register_fullname.error = "Nama tidak valid"
            }
            if(email.isEmpty()){
                et_register_email.error = "Email tidak valid"
            }
            if(password.isEmpty()){
                et_register_password.error = "Password tidak valid"
            }
            if(confirmPass.isEmpty()){
                et_register_fullname.error = "Isi kembali password"
            }
            if(password != confirmPass){
                Toast.makeText(this,"Konfirmasi password kamu salah!",Toast.LENGTH_SHORT).show()
            }

            registerUser(userName, email, password)
        }

        tv_login_here.setOnClickListener{
            val intentLogin = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }

    private fun registerUser(userName:String,email:String,password:String){
        loading_register.visibility = View.VISIBLE
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                loading_register.visibility = View.GONE
                if(it.isSuccessful){
                    val user:FirebaseUser? = auth.currentUser
                    val userId : String? = user?.uid

                    databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)

                    val hashMap:HashMap<String,String> = HashMap()
                    hashMap["userId"] = userId
                    hashMap["username"] = userName
                    hashMap["profileImage"] = ""

                    databaseReference.setValue(hashMap).addOnCompleteListener(this){database->
                        if(database.isSuccessful){
                            //intent ke home
                            val intentHome = Intent(this@SignUpActivity, MainActivity::class.java)
                            startActivity(intentHome)
                        }
                    }
                }
            }
    }

}
