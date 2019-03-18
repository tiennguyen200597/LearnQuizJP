package com.app.learnquizjp.activity.ui.test

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.R
import com.app.learnquizjp.activity.TestActivity
import kotlinx.android.synthetic.main.test_fragment.*

class TestFragment : Fragment() {
    val ARG_PAGE = "page"
    var mPageNumber: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_bmquiz.text="Câu "+(mPageNumber+1)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activitiTest: TestActivity= TestActivity()
        var arrtest: ArrayList<Int>
        arrtest=activitiTest.getData()
        mPageNumber = arguments!!.getInt(ARG_PAGE)
    }

      public fun create(pageNumber: Int): TestFragment {
        val fragment:TestFragment = TestFragment()
        val args = Bundle()
        args.putInt(ARG_PAGE, pageNumber)
        fragment.setArguments(args)
        return fragment
    }

}
