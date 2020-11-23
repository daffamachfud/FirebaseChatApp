package com.onoh.firebasechatapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.onoh.firebasechatapp.ui.MainActivity
import com.onoh.firebasechatapp.R
import com.onoh.firebasechatapp.ui.user.UserActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btn_login_submit.setOnClickListener{
             val email = et_login_email.text.toString()
             val password = et_login_password.text.toString()

            if(email.isEmpty() && password.isEmpty()){
                Toast.makeText(this,"Email dan password harap diisi ! ",Toast.LENGTH_SHORT).show()
            }
            else{
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        if(it.isSuccessful){
                            val intentMain = Intent(this@LoginActivity, UserActivity::class.java)
                            startActivity(intentMain)
                        }else{
                            Toast.makeText(this,"email atau password salah! ",Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }

        tv_signup_here.setOnClickListener{
            val intentRegister = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intentRegister)
        }
    }
}
