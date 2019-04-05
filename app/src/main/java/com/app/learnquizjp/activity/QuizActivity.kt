package com.app.learnquizjp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.learnquizjp.R
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        btn_practice.setOnClickListener {
            startActivity(Intent(this,ChooseLevelPracticeActivity::class.java))
        }
        btn_test.setOnClickListener {
            startActivity(Intent(this,ChooseLevelTestActivity::class.java))
        }
    }
}
