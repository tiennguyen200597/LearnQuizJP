package com.app.learnquizjp.fragment

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.R
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.nav_header_home.*

class UserFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_user, container, false)
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