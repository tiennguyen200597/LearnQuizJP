package com.app.learnquizjp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.app.learnquizjp.R
import com.app.learnquizjp.model.Question


class ChkAnswerAdapter(private var data: ArrayList<Question>, context: Context) :
    RecyclerView.Adapter<ChkAnswerAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_resource_test, parent, false)
        return RecyclerViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.tv_position.text = (position + 1).toString()
        if (data[position].qzstatuschk != "") {
            holder.ll_position.setBackgroundColor(R.color.grayburn)
            when(data[position].qzstatuschk){
                "a" -> holder.rad_a.isChecked=true
                "b" -> holder.rad_b.isChecked=true
                "c" -> holder.rad_c.isChecked=true
                "d" -> holder.rad_d.isChecked=true
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tv_position: TextView = itemView.findViewById(R.id.tv_position)
        internal var rad_a: RadioButton = itemView.findViewById(R.id.rad_a)
        internal var rad_b: RadioButton = itemView.findViewById(R.id.rad_b)
        internal var rad_c: RadioButton = itemView.findViewById(R.id.rad_c)
        internal var rad_d: RadioButton = itemView.findViewById(R.id.rad_d)
        internal var ll_position: LinearLayout = itemView.findViewById(R.id.ll_position)



    }
}


