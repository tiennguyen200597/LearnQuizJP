package com.app.learnquizjp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import com.app.learnquizjp.R

import java.nio.file.Files.size


class ChkAnswerAdapter(internal var data: java.util.ArrayList<*>, context: Context) : RecyclerView.Adapter<ChkAnswerAdapter.RecyclerViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_resource_test, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.tv_position.text= data[position].toString()
        holder.rad_group.isEnabled=false
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var tv_position: TextView
        internal var rad_group: RadioGroup

        init {
            tv_position = itemView.findViewById(R.id.tv_position)
            rad_group = itemView.findViewById(R.id.rad_group)
        }
    }
}


