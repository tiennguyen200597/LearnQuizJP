package com.app.learnquizjp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.R
import com.app.learnquizjp.adapter.DefaultAdapter
import com.app.learnquizjp.dao.KotobaDAO
import com.app.learnquizjp.model.Kotoba
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_favorite.view.rvKotoba

class FavoriteFragment : Fragment(){

    private var test : MutableList<Int>? = mutableListOf()
    private var data : MutableList<Kotoba> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_favorite, container, false)
        val user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val ref = FirebaseDatabase.getInstance().reference
        val favo = ref.child("user").child(user!!.uid).child("favorite")

        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var index : Int? = Integer.parseInt(dataSnapshot.children.toString())
                Log.e("index",index.toString())
//                val snapshotIterator : Iterable<DataSnapshot> = dataSnapshot.children
//                val iterator : Iterator<DataSnapshot> = snapshotIterator.iterator()
//                test?.clear()
//                while(iterator.hasNext()){
//                    val next : DataSnapshot = iterator.next()
//
//                    if (index != null) {
//                        test?.add(index)
//                    }
//                }
//                Log.e("key",key.toString())
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("result", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        favo.addValueEventListener(userListener)

        for(i in 0 until test!!.size){
            KotobaDAO(view.context).getSearchKotoba(i)?.let { data.add(it) }
        }

        val defaultAdapter = DefaultAdapter(data)
        val linearLayoutManager = LinearLayoutManager(view!!.context)
        view!!.rvKotoba.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = defaultAdapter
        }
        return view
    }
}