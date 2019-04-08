package com.app.learnquizjp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import com.app.learnquizjp.R


class UserFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_user, container, false)
        var auth : FirebaseAuth = FirebaseAuth.getInstance()
        var user : FirebaseUser? = auth.currentUser
        var authListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(Intent(activity, LoginActivity::class.java))
                activity!!.finish()
            }
        }
        return view
    }

//    private fun getUserInformation(){
//        var sharedPreferences = container.getSharedPreferences("USER_ACCOUNT",MODE_PRIVATE)
//        var username : String = sharedPreferences.getString("USERNAME","")
//        var email : String = sharedPreferences.getString("EMAIL","")
//        tvUsername.text = username
//        tvEmail.text = email
//    }
}