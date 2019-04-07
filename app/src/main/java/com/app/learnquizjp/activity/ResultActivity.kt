package com.app.learnquizjp.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.app.learnquizjp.R
import com.app.learnquizjp.model.Question

import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.content_result.*

class ResultActivity : AppCompatActivity() {
    var listQuestionQri : ArrayList<Question> = ArrayList()
    var dataChkQz : ArrayList<Question> = ArrayList()
    var totalTrue: Int=0
    var totalFail: Int=0
    var totalDoNot: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent = intent
        val bd = intent.extras
        if (bd != null) {
            listQuestionQri = bd.get("listQuestionQri") as ArrayList<Question>
            dataChkQz = bd.get("dataChkQz") as ArrayList<Question>
        }
        for (i in 0..(dataChkQz.size-1)){
            if (dataChkQz[i].qzstatuschk==0){
                totalDoNot+=1
            }
            else if  (dataChkQz[i].ascurrent.equals(listQuestionQri[i].ascortect)){
                totalTrue+=1
            }
            else if (dataChkQz[i].ascurrent!="" &&dataChkQz[i].ascurrent.equals(listQuestionQri[i].ascortect)==false){
                totalFail+=1
            }

        }
        tv_result.text="${totalTrue}/${dataChkQz.size}"
        tv_totalTrue.text="${totalTrue}/${dataChkQz.size}"
        tv_totalFail.text="${totalFail}/${dataChkQz.size}"
        tv_totalDoNot.text="${totalDoNot}/${dataChkQz.size}"
        var review:Boolean=true
        btn_review.setOnClickListener {
            var inreview:Intent= Intent(this@ResultActivity,ReviewActivity::class.java)
            inreview.putExtra("listQuestionQri",listQuestionQri)
            inreview.putExtra("dataChkQz",dataChkQz)
            startActivity(inreview)
        }




    }

}
