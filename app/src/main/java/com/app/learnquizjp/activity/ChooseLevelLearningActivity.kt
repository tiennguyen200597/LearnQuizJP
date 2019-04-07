package com.app.learnquizjp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import com.app.learnquizjp.R
import com.app.learnquizjp.adapter.LearningAdapter

import kotlinx.android.synthetic.main.activity_choose_level_learning.*
import kotlinx.android.synthetic.main.content_choose_level_learning.*

class ChooseLevelLearningActivity : AppCompatActivity() {

    private var data : MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_level_learning)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        addLessonData()
        addLessonList()
    }

    private fun addLessonData(){
        data!!.add(0,"a")
        data!!.add(1,"b")
        data!!.add(2,"c")
        data!!.add(3,"d")
        data!!.add(4,"e")
        data!!.add(5,"f")
        data!!.add(6,"g")
        data!!.add(7,"h")
        data!!.add(8,"i")
        data!!.add(9,"j")
    }

    private fun addLessonList(){
        var learningAdapter = LearningAdapter(data!!)
        var linearLayoutManager = LinearLayoutManager(this)
        rvLesson.layoutManager = linearLayoutManager
        rvLesson.setHasFixedSize(true)
        rvLesson.adapter = learningAdapter
    }


}
