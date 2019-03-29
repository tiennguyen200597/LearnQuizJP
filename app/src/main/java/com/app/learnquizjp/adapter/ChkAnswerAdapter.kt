package com.app.learnquizjp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.R
import com.app.learnquizjp.model.Question
import kotlinx.android.synthetic.main.item_resource_test.view.*


class ChkAnswerAdapter(private var data : ArrayList<Question>,val context: Context) :
    RecyclerView.Adapter<ChkAnswerAdapter.AnswerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_resource_test, parent, false)
        return AnswerHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder : AnswerHolder, position : Int) {
        holder.tv_position.text = (position + 1).toString()
        if (data[position].qzstatuschk != "") {
            holder.ll_position.setBackgroundColor(R.color.grayburn)
            when(data[position].qzstatuschk){
                "a" -> holder.rad_a.isChecked = true
                "b" -> holder.rad_b.isChecked = true
                "c" -> holder.rad_c.isChecked = true
                "d" -> holder.rad_d.isChecked = true
                else -> ""
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class AnswerHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_position = itemView.tv_position!!
        var rad_a = itemView.rad_a!!
        var rad_b = itemView.rad_b!!
        var rad_c = itemView.rad_c!!
        var rad_d = itemView.rad_d!!
        var ll_position = itemView.ll_position!!
    }

}


