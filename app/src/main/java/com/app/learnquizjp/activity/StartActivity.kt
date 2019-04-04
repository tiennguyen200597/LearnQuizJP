package com.app.learnquizjp.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import com.app.learnquizjp.R
import com.app.learnquizjp.base.MyBounceInterpolator
import kotlinx.android.synthetic.main.activity_start.*
import java.util.*


class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.app.learnquizjp.R.layout.activity_start)
        var animation = AnimationUtils.loadAnimation(this@StartActivity,R.anim.bounce)
        var myBounceInterpolator = MyBounceInterpolator(0.2,10.0)
        animation.interpolator = myBounceInterpolator
        imgLogo.startAnimation(animation)

        // Count down interval 3 second
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(this@StartActivity,LoginActivity::class.java))
                finish()
            }
        }, 3000)
    }
}
