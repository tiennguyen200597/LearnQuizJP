package com.app.learnquizjp.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.app.learnquizjp.R
import com.app.learnquizjp.fragment.QuizFragment
import com.app.learnquizjp.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val quizFragment: QuizFragment = QuizFragment()
    private val settingFragment : SettingFragment = SettingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.learnquizjp.R.layout.activity_home)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            com.app.learnquizjp.R.string.navigation_drawer_open,
            com.app.learnquizjp.R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(com.app.learnquizjp.R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            com.app.learnquizjp.R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            com.app.learnquizjp.R.id.nav_learning -> {
                // Handle the camera action
                startActivity(Intent(this@HomeActivity,LearningActivity::class.java))
                finish()
            }
            com.app.learnquizjp.R.id.nav_quiz -> {
                showFragmentQuiz()
            }
            com.app.learnquizjp.R.id.nav_setting -> {
                showFragmentSetting()
            }
            com.app.learnquizjp.R.id.nav_feedback -> {

            }
            com.app.learnquizjp.R.id.nav_about -> {

            }
            com.app.learnquizjp.R.id.nav_log_out -> {
                startActivity(Intent(this@HomeActivity,LoginActivity::class.java))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    fun showFragmentQuiz(){
        var fragmentManager: FragmentManager=supportFragmentManager
        var fragmentTransaction: FragmentTransaction=fragmentManager.beginTransaction()
        if (quizFragment.isAdded){
            fragmentTransaction.show(quizFragment)
        }
        else{
            fragmentTransaction.add(R.id.container,quizFragment)
        }
        fragmentTransaction.commit()
    }

    private fun showFragmentSetting(){
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        if(settingFragment.isAdded){
            fragmentTransaction.show(settingFragment)
        }else{
            fragmentTransaction.add(R.id.container,settingFragment)
        }
        fragmentTransaction.commit()
    }

}
