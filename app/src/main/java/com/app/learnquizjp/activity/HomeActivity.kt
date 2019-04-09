package com.app.learnquizjp.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.app.learnquizjp.R
import com.app.learnquizjp.fragment.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val homeFragment : HomeFragment = HomeFragment()
    private val settingFragment : SettingFragment = SettingFragment()
    private val feedbackFragment : FeedbackFragment = FeedbackFragment()
    private val aboutFragment : AboutFragment = AboutFragment()
    private val userFragment : UserFragment = UserFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.learnquizjp.R.layout.activity_home)
        setSupportActionBar(toolbar)
        showFragment(homeFragment)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            com.app.learnquizjp.R.string.navigation_drawer_open,
            com.app.learnquizjp.R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        getUserInformation()
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
        var auth : FirebaseAuth = FirebaseAuth.getInstance()
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> showFragment(homeFragment)
            R.id.nav_learning -> startActivity(Intent(this, LearningActivity::class.java))
            R.id.nav_quiz -> startActivity(Intent(this,QuizActivity::class.java))
            R.id.nav_setting -> showFragment(settingFragment)
            R.id.nav_feedback -> showFragment(feedbackFragment)
            R.id.nav_about -> showFragment(aboutFragment)
            R.id.nav_log_out -> {
                auth.signOut()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getUserInformation(){
        var sharedPreferences = getSharedPreferences("USER_ACCOUNT",MODE_PRIVATE)
        var email : String = sharedPreferences.getString("EMAIL","")
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val header = navigationView.getHeaderView(0)
        header.tvEmail.text = email
        header.imgUserAva.setOnClickListener {
            showFragment(userFragment)
            drawer_layout.closeDrawer(GravityCompat.START,false)
        }
    }

    private fun showFragment(fragment : Fragment){
        var fragmentManager : FragmentManager = supportFragmentManager
        var fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        if(fragment.isAdded){
            fragmentTransaction.show(fragment)
        }else{
            fragmentTransaction.replace(R.id.container,fragment)
        }
        fragmentTransaction.commit()
    }

}
