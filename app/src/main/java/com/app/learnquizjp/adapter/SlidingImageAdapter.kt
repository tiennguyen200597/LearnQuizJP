package com.app.learnquizjp.adapter

import android.content.Context
import android.os.Parcelable
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.app.learnquizjp.R
import com.app.learnquizjp.model.Image
import java.util.ArrayList

class SlidingImageAdapter(private val context: Context, private val imageModelArrayList: ArrayList<Image>) : PagerAdapter() {
    private val inflater: LayoutInflater


    init {
        inflater = LayoutInflater.from(context)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageModelArrayList.size
    }

    override fun instantiateItem(view: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.slidingimages_layout, view, false)!!

        val imageView = imageLayout
            .findViewById(R.id.image) as ImageView


        imageView.setImageResource(imageModelArrayList[position].getImage_drawables())

        view.addView(imageLayout, 0)

        return imageLayout
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {}

    override fun saveState(): Parcelable? {
        return null
    }


}