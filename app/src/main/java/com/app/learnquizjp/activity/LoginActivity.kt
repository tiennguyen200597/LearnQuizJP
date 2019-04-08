package com.app.learnquizjp.activity


import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.app.learnquizjp.R

import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.learnquizjp.R.layout.activity_login)
        //Get SharePreference to get the last status
        getLastLoginStatus()

        // Set up the login form.
        btnLogin.setOnClickListener {
            checkLoginInformation()
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
            finish()
        }

    }

    private fun getLastLoginStatus(){
        var sharedPreferences = getSharedPreferences("USER_ACCOUNT",MODE_PRIVATE)
        cbRemember.isChecked = sharedPreferences.getBoolean("STATUS",false)
        if(cbRemember.isChecked){
            edtUsername.setText(sharedPreferences.getString("USERNAME",""))
            edtPassword.setText(sharedPreferences.getString("PASSWORD","1234"))
        }else{
            edtUsername.text = null
            edtPassword.text = null
        }
    }

    private fun checkLoginInformation(){
        var username : String = edtUsername.text.toString().trim()
        var password : String = edtPassword.text.toString().trim()
        if(password.length < 4 || username.isEmpty() || password.isEmpty()){

            if(username.isEmpty()){
                edtUsername.error = getString(R.string.notify_empty_user);
            }

            if(password.length < 4){
                edtPassword.error = getString(R.string.notify_length_pass);
            }

            if(password.isEmpty()){
                edtPassword.error = getString(R.string.notify_empty_pass);
            }

        }else{
            if((username == "admin" && password == "admin") ||
                (((username == "namnt") || (username == "tiennv")) && password == getSharedPreferences("USER_ACCOUNT",MODE_PRIVATE).getString("PASSWORD","1234"))){
                rememberUserInfomation(username,password,cbRemember.isChecked)
                Toast.makeText(this@LoginActivity,getString(R.string.login_successful),Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                finish()
            }else{
                Toast.makeText(this@LoginActivity,getString(R.string.login_fail),Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun rememberUserInfomation(u : String,p : String,status : Boolean){
        var sharePreferences = this.getSharedPreferences("USER_ACCOUNT", Context.MODE_PRIVATE)
        val editor = sharePreferences!!.edit()
        editor.clear()
        editor.putString("USERNAME",u)
        editor.putString("PASSWORD",p)
        editor.putBoolean("STATUS",status)
        editor.apply()
    }

}
