package com.app.learnquizjp.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.content.Intent
import android.graphics.Color
import android.view.WindowManager
import android.os.Build
import android.support.annotation.Nullable
import android.text.Html
import android.widget.TextView
import android.support.v4.view.ViewPager
import android.widget.Button
import android.widget.LinearLayout
import com.app.learnquizjp.R
import com.app.learnquizjp.base.PrefManager

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class WelcomeActivity : AppCompatActivity() {
    private var mViewPager: ViewPager? = null
    private var mPagerAdapter: IntroViewPagerAdapter? = null
    private var mDotsLayout: LinearLayout? = null
    private var mDots: Array<TextView>? = null
    private var mLayouts: IntArray? = null
    private var mBtnSkip: Button? = null
    private var mBtnNext: Button? = null
    private val mPrefManager: PrefManager? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PrefManager.getInstance(this).isFirstTimeLaunch) {
            launchHomeScreen()
        }

        //Making notification bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        }
        setContentView(R.layout.activity_welcome)

        mViewPager = findViewById<ViewPager>(R.id.view_pager)
        mDotsLayout = findViewById<LinearLayout>(R.id.layoutDots)
        mBtnSkip = findViewById<Button>(R.id.btn_skip)
        mBtnNext = findViewById<Button>(R.id.btn_next)

        /**
         * Layouts of all welcome slides
         * add few more layouts if you want
         */
        mLayouts = intArrayOf(
            R.layout.welcome_slide1,
            R.layout.welcome_slide2,
            R.layout.welcome_slide3,
            R.layout.welcome_slide4
        )
        addBottomDots(0)
        changeStatusBarColor()

        mPagerAdapter = IntroViewPagerAdapter()
        mViewPager!!.adapter = mPagerAdapter
        mViewPager!!.addOnPageChangeListener(mViewPagerChangeListener)

        mBtnSkip!!.setOnClickListener(View.OnClickListener { launchHomeScreen() })

        mBtnNext!!.setOnClickListener(View.OnClickListener {
            /**
             * Checking for last page if last page home screen will be launched
             */
            /**
             * Checking for last page if last page home screen will be launched
             */
            val current = getItem(1)
            if (current < mLayouts!!.size) {
                // move to nex screen
                mViewPager!!.currentItem = current
            } else {
                launchHomeScreen()
            }
        })
    }

    private val mViewPagerChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            //Change the next button text 'NEXT'/'GOT IT'
            if (position == mLayouts!!.size - 1) {
                mBtnNext!!.setText(getString(R.string.start))
                mBtnSkip!!.setVisibility(View.GONE)
            } else {
                //Still pages are left
                mBtnNext!!.setText(getString(R.string.next))
                mBtnSkip!!.setVisibility(View.VISIBLE)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {

        }
    }

    private fun addBottomDots(currentPage: Int) {
        mDots = arrayOfNulls<TextView?>(mLayouts!!.size)

        val colorsActive = resources.getIntArray(R.array.array_dot_active)
        val colorsInActive = resources.getIntArray(R.array.array_dot_inactive)

        mDotsLayout!!.removeAllViews()
        for (i in mDots!!.indices) {
            mDots[i] = TextView(this)
            mDots!![i].text = Html.fromHtml("•")
            mDots!![i].textSize = 35f
            mDots!![i].setTextColor(colorsInActive[currentPage])
            mDotsLayout!!.addView(mDots!![i])
        }
        if (mDots!!.size > 0) {
            mDots!![currentPage].setTextColor(colorsActive[currentPage])
        }
    }

    /**
     * Making notification bar transparent
     */
    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun getItem(i: Int): Int {
        return mViewPager!!.currentItem + i
    }

    private fun launchHomeScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }


    inner class IntroViewPagerAdapter : PagerAdapter() {
        private var mInflater: LayoutInflater? = null

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            mInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = mInflater!!.inflate(mLayouts!![position], container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return mLayouts!!.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}
