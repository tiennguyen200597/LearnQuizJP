package com.app.learnquizjp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import com.app.learnquizjp.R
import com.app.learnquizjp.activity.ui.test.TestFragment
import com.app.learnquizjp.adapter.SlidingImageAdapter
import com.app.learnquizjp.model.Image
import kotlinx.android.synthetic.main.test_activity.*
import java.util.*
import kotlin.collections.ArrayList



class TestActivity : AppCompatActivity() {
    val NUM_PAGES = 20
    var mPager: ViewPager? = null
    var mPagerAdapter: PagerAdapter? = null
    var arrtest:ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)

        // tạo mảng chứa dữ liệu test
        for (item: Int in 1..20) {
            arrtest.add(item)
        }

        mPager = findViewById(R.id.pager) as ViewPager
        mPagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager!!.setAdapter(mPagerAdapter)
        mPager!!.setPageTransformer(true, DepthPageTransformer())






    }

    fun getData(): ArrayList<Int> {
        return arrtest
    }

     inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
         var testFragment: TestFragment= TestFragment()
         // vị trí hiện tại của view page
        override fun getItem(position: Int): Fragment {
        return testFragment.create(position)
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }

    inner class DepthPageTransformer : ViewPager.PageTransformer {
        val MIN_SCALE = 0.75f

        override fun transformPage(view: View, position: Float) {
            val pageWidth = view.width

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.alpha = 1f
                view.translationX = 0f
                view.scaleX = 1f
                view.scaleY = 1f

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.alpha = 1 - position

                // Counteract the default slide transition
                view.translationX = pageWidth * -position

                // Scale the page down (between MIN_SCALE and 1)
                val scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
            }
        }


    }




    }


