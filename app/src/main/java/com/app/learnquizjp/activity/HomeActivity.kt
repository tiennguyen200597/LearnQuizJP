package com.app.learnquizjp.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.app.learnquizjp.R
import com.app.learnquizjp.fragment.AboutFragment
import com.app.learnquizjp.fragment.FeedbackFragment
import com.app.learnquizjp.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val settingFragment : SettingFragment = SettingFragment()
    private val feedbackFragment : FeedbackFragment = FeedbackFragment()
    private val aboutFragment : AboutFragment = AboutFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.learnquizjp.R.layout.activity_home)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
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
        return when (item.itemId) {
            com.app.learnquizjp.R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            com.app.learnquizjp.R.id.nav_home -> {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            com.app.learnquizjp.R.id.nav_learning -> {
                // Handle the camera action
                startActivity(Intent(this, LearningActivity::class.java))
            }
            com.app.learnquizjp.R.id.nav_quiz -> {
                startActivity(Intent(this,QuizActivity::class.java))
            }
            com.app.learnquizjp.R.id.nav_setting -> {
                showFragmentSetting()
            }
            com.app.learnquizjp.R.id.nav_feedback -> {
                showFragmentFeedback()
            }
            com.app.learnquizjp.R.id.nav_about -> {
                showFragmentAbout()
            }
            com.app.learnquizjp.R.id.nav_log_out -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
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

    private fun showFragmentFeedback(){
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        if(feedbackFragment.isAdded){
            fragmentTransaction.show(feedbackFragment)
        }else{
            fragmentTransaction.add(R.id.container,feedbackFragment)
        }
        fragmentTransaction.commit()
    }

    private fun showFragmentAbout(){
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        if(aboutFragment.isAdded){
            fragmentTransaction.show(aboutFragment)
        }else{
            fragmentTransaction.add(R.id.container,aboutFragment)
        }
        fragmentTransaction.commit()
    }

}
