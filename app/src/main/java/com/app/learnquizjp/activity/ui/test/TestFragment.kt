package com.app.learnquizjp.activity.ui.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.R
import com.app.learnquizjp.activity.TestActivity
import com.app.learnquizjp.model.Question
import kotlinx.android.synthetic.main.test_fragment.*
import java.util.*


class TestFragment : Fragment() {
    val ARG_PAGE = "page"
    var mPageNumber: Int = 0
    var loatASls: ArrayList<Question> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.test_fragment, container, false)
    }

    @SuppressLint("ResourceAsColor")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tv_bmquiz.text = "Câu " + (mPageNumber + 1)
        tv_question.text = loatASls[mPageNumber].qzcontent
        /*tv_answerA.text = loatASls[mPageNumber].ascortect
        tv_answerB.text = loatASls[mPageNumber].asincortecT1
        tv_answerC.text = loatASls[mPageNumber].asincortecT2
        tv_answerD.text = loatASls[mPageNumber].asincortecT3*/
        rad_answerA.text = loatASls[mPageNumber].ascortect
        rad_answerB.text = loatASls[mPageNumber].asincortecT1
        rad_answerC.text = loatASls[mPageNumber].asincortecT2
        rad_answerD.text = loatASls[mPageNumber].asincortecT3

        /*ll_answerA.setOnClickListener {
            tv_answerA.setBackgroundResource(R.color.bluelight)
            tv_answerB.setBackgroundResource(R.color.blacklight)
            tv_answerC.setBackgroundResource(R.color.blacklight)
            tv_answerD.setBackgroundResource(R.color.blacklight)
            rad_answerA.isChecked = true
        }
        ll_answerB.setOnClickListener {
            tv_answerA.setBackgroundResource(R.color.blacklight)
            tv_answerB.setBackgroundResource(R.color.bluelight)
            tv_answerC.setBackgroundResource(R.color.blacklight)
            tv_answerD.setBackgroundResource(R.color.blacklight)
            rad_answerB.isChecked = true
        }
        ll_answerC.setOnClickListener {
            tv_answerA.setBackgroundResource(R.color.blacklight)
            tv_answerB.setBackgroundResource(R.color.blacklight)
            tv_answerC.setBackgroundResource(R.color.bluelight)
            tv_answerD.setBackgroundResource(R.color.blacklight)
            rad_answerC.isChecked = true
        }
        ll_answerD.setOnClickListener {
            tv_answerA.setBackgroundResource(R.color.blacklight)
            tv_answerB.setBackgroundResource(R.color.blacklight)
            tv_answerC.setBackgroundResource(R.color.blacklight)
            tv_answerD.setBackgroundResource(R.color.bluelight)
            rad_answerD.isChecked = true
        }*/

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // call activy
        val activitiTest = activity as TestActivity?

        if (activitiTest != null) {
            loatASls = activitiTest.getData()
        }

        mPageNumber = arguments!!.getInt(ARG_PAGE)

    }

    fun create(pageNumber: Int): TestFragment {
        val fragment: TestFragment = TestFragment()
        val args = Bundle()
        args.putInt(ARG_PAGE, pageNumber)
        fragment.arguments = args
        return fragment
    }


}


