package com.app.learnquizjp.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.app.learnquizjp.R
import com.app.learnquizjp.adapter.KotobaAdapter
import com.app.learnquizjp.base.RecyclerItemClickListener
import com.app.learnquizjp.dao.KotobaDAO
import com.app.learnquizjp.model.Kotoba
import kotlinx.android.synthetic.main.fragment_home.view.*
import com.facebook.FacebookSdk.getApplicationContext
import android.widget.PopupMenu




class HomeFragment : Fragment(){

    private var data : MutableList<Kotoba> = mutableListOf()
    private var index : MutableList<Int> = mutableListOf()
    private lateinit var kotobaDAO : KotobaDAO
    //private lateinit var auth : FirebaseAuth
    //private lateinit var storage : FirebaseStorage

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View = inflater.inflate(R.layout.fragment_home, container, false)
        //auth = FirebaseAuth.getInstance()
        //val user : FirebaseUser? = auth.currentUser
        //storage = FirebaseStorage.getInstance()
        kotobaDAO = KotobaDAO(view.context)
        data.clear()
        kotobaDAO.initKotobaData()
        data = kotobaDAO.getAllKotoba()
        //Add Default kotoba to MutableList and show to HomeFragment
        addKotobaList(view,data)

        view.btnSearch.setOnClickListener {
            val searchKey : String? = view.edtKey.text.toString()
            if(searchKey != null){
                val kotoba : Kotoba? = kotobaDAO.getSearchKotoba(searchKey)
                val searchData : MutableList<Kotoba> = mutableListOf()
                if (kotoba != null) {
                    searchData.add(kotoba)
                    addKotobaList(view,searchData)
                }else{
                    Toast.makeText(activity,getString(R.string.notify_search_no_result),Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(activity,getString(R.string.notify_empty_search_value),Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    private fun addKotobaList(v : View,d : MutableList<Kotoba>){
        val kotobaAdapter = KotobaAdapter(d)
        val linearLayoutManager = LinearLayoutManager(v!!.context)
        v!!.rvKotoba.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = kotobaAdapter
        }
        v!!.rvKotoba.addOnItemTouchListener(
            RecyclerItemClickListener(activity!!.applicationContext,object : RecyclerItemClickListener.OnItemClickListener{
                override fun onItemClick(view: View, position: Int) {
                    val popup = PopupMenu(getApplicationContext(), view)
                    popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
                    setForceShowIcon(popup)
                    popup.setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.action_love -> {
                                if(index.size == 0){
                                    index.add(position)
                                }else{
                                    for(i in 0 until index.size){
                                        if(index[i] == position){
                                            index.removeAt(i)
                                        }
                                    }
                                    index.add(position)
                                }
                                Log.e("index",index.toString())
                                popup.dismiss()
                            }
                        }
                        true
                    }
                    popup.show()
                }
            })
        )
    }

    /*private fun saveUserToFirebaseDatabase(favorite : MutableList<Kotoba>){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid, FirebaseAuth.getInstance().currentUser!!.email!!,favorite)
        ref.setValue(user)
            .addOnSuccessListener {
                Toast.makeText(activity,getString(R.string.notify_save_user_successful),Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{
                Toast.makeText(activity,getString(R.string.notify_save_user_fail),Toast.LENGTH_SHORT).show()
            }
    }*/

    private fun setForceShowIcon(popupMenu: PopupMenu) {
        try {
            val mFields = popupMenu.javaClass.declaredFields
            for (field in mFields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field.get(popupMenu)
                    val popupHelper = Class.forName(menuPopupHelper.javaClass.name)
                    val mMethods = popupHelper.getMethod("setForceShowIcon", Boolean::class.javaPrimitiveType!!)
                    mMethods.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }

    }
}