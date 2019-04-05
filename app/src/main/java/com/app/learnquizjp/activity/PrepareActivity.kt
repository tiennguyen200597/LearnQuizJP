package com.app.learnquizjp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.app.learnquizjp.R
import com.app.learnquizjp.model.ABCDQuestion
import com.app.learnquizjp.model.Question

import kotlinx.android.synthetic.main.activity_prepare.*
import kotlinx.android.synthetic.main.content_prepare.*

class PrepareActivity : AppCompatActivity() {
    // list for change position
    var lsQS: ArrayList<String> = ArrayList()
    var arrtest: ArrayList<Question> = ArrayList()
    var listQuestion: ArrayList<Question> = ArrayList()
    val arrAnswer: ArrayList<ABCDQuestion> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        for (i: Int in 0..34) {
            var question = Question(
                i,
                "学費はすべてアルバイトで 賄って いる ${i + 1}",
                "しはらって ${i + 1}",
                "まかなって ${i + 1}",
                "うるおって ${i + 1}",
                "ふるって ${i + 1}",
                "Đáp án chính xác là đéo biết",
                5,
                5
            )
            listQuestion.add(question)
        }
        arrtest=listQuestion
        //sort
        for (i in 0..34) {
            var abcdQuestion = ABCDQuestion()
            lsQS.add(arrtest[i].ascortect!!)
            lsQS.add(arrtest[i].asincortecT1!!)
            lsQS.add(arrtest[i].asincortecT2!!)
            lsQS.add(arrtest[i].asincortecT3!!)
            lsQS.shuffle()
            abcdQuestion.ascortect = lsQS[0]
            abcdQuestion.asincortecT1 = lsQS[1]
            abcdQuestion.asincortecT2 = lsQS[2]
            abcdQuestion.asincortecT3 = lsQS[3]
            arrAnswer.add(abcdQuestion)
            arrtest[i].ascortect = arrAnswer[i].ascortect
            arrtest[i].asincortecT1 = arrAnswer[i].asincortecT1
            arrtest[i].asincortecT2 = arrAnswer[i].asincortecT2
            arrtest[i].asincortecT3 = arrAnswer[i].asincortecT3
            lsQS.removeAll(lsQS)
        }

        btn_start.setOnClickListener {
            var intent = Intent(this@PrepareActivity,TestActivity::class.java)
            intent.putExtra("listQuestion",arrtest)
            intent.putExtra("listQuestionQri",listQuestion)
            startActivity(intent)
        }
    }

}
