package com.app.learnquizjp.fragment
import android.content.Intent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.learnquizjp.R
import com.app.learnquizjp.activity.ChooseLvActivity
import kotlinx.android.synthetic.main.fragment_quiz.*


class QuizFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View=inflater.inflate(R.layout.fragment_quiz, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_test.setOnClickListener {
            startActivity(Intent(context, ChooseLvActivity::class.java))
        }

    }


}
