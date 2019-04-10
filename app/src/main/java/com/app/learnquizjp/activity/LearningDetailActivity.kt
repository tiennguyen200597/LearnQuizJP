package com.app.learnquizjp.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import com.app.learnquizjp.R
import com.app.learnquizjp.fragment.GrammarFragment
import com.app.learnquizjp.fragment.KanjiFragment
import com.app.learnquizjp.fragment.WordFragment
import com.app.learnquizjp.base.BottomNavigationBehavior
import android.support.design.widget.CoordinatorLayout

class LearningDetailActivity : AppCompatActivity() {

    private lateinit var toolbar : ActionBar

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment : Fragment
        when (item.itemId) {
            com.app.learnquizjp.R.id.nav_bot_word -> {
                toolbar!!.title = getString(com.app.learnquizjp.R.string.title_word)
                fragment = WordFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            com.app.learnquizjp.R.id.nav_bot_grammar -> {
                toolbar!!.title = getString(com.app.learnquizjp.R.string.title_grammar)
                fragment = GrammarFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            com.app.learnquizjp.R.id.nav_bot_kanji -> {
                toolbar!!.title = getString(com.app.learnquizjp.R.string.title_kanji)
                fragment = KanjiFragment()
                loadFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning_detail)

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // attaching bottom sheet behaviour - hide / show on scroll
        val layoutParams = navigation.layoutParams as CoordinatorLayout.LayoutParams
        layoutParams.behavior = BottomNavigationBehavior()

        toolbar = supportActionBar!!

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        toolbar!!.title = getString(com.app.learnquizjp.R.string.title_word)
        loadFragment(WordFragment())
    }

    private fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(com.app.learnquizjp.R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
