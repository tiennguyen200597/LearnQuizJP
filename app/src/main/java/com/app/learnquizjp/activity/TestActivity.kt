package com.app.learnquizjp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewPager
import com.app.learnquizjp.R
import com.app.learnquizjp.adapter.SlidingImageAdapter
import com.app.learnquizjp.model.Image
import java.util.*

class TestActivity : AppCompatActivity() {
    var imageModelArrayList: ArrayList<Image>? = null

    val myImageList = intArrayOf(R.drawable.abc_ab_share_pack_mtrl_alpha, R.drawable.abc_action_bar_item_background_material, R.drawable.abc_btn_check_material, R.drawable.abc_btn_check_to_on_mtrl_000, R.drawable.abc_btn_check_to_on_mtrl_015, R.drawable.abc_btn_default_mtrl_shape)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity)

/*        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TestFragment.newInstance())
                .commitNow()

        }*/
        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()
        init()
    }


        fun populateList(): ArrayList<Image> {

            val list = ArrayList<Image>()

            for (i in 0..5) {
                val imageModel = Image()
                imageModel.setImage_drawables(myImageList[i])
                list.add(imageModel)
            }

            return list
        }

         fun init() {

            mPager = findViewById(R.id.pager) as ViewPager
            mPager!!.adapter = SlidingImageAdapter(this@TestActivity, this.imageModelArrayList!!)


            val density = resources.displayMetrics.density


            NUM_PAGES = imageModelArrayList!!.size

            // Auto start of viewpager
            val handler = Handler()
            val Update = Runnable {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0
                }
                mPager!!.setCurrentItem(currentPage++, true)
            }

        }

        companion object {

            private var mPager: ViewPager? = null
            private var currentPage = 0
            private var NUM_PAGES = 0
        }
    }


