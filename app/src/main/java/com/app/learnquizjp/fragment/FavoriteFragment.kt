package com.app.learnquizjp.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.learnquizjp.R
import com.app.learnquizjp.adapter.DefaultAdapter
import com.app.learnquizjp.model.Kotoba
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_favorite.view.rvKotoba


class FavoriteFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_favorite, container, false)

//        val defaultAdapter = DefaultAdapter(data)
//        val linearLayoutManager = LinearLayoutManager(view!!.context)
//        view!!.rvKotoba.apply {
//            setHasFixedSize(true)
//            layoutManager = linearLayoutManager
//            adapter = defaultAdapter
//        }
        return view
    }
}