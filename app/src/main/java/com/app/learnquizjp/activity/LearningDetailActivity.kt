package com.app.learnquizjp.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.app.learnquizjp.R
import kotlinx.android.synthetic.main.activity_learning_detail.*

class LearningDetailActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_bot_word -> {
                message.text = getString(R.string.title_word)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_bot_grammar -> {
                message.text = getString(R.string.title_grammar)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_bot_kanji -> {
                message.text = getString(R.string.title_kanji)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_detail)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
