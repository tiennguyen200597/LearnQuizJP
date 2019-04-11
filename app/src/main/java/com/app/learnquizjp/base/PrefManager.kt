package com.app.learnquizjp.base

import android.content.Context
import android.content.SharedPreferences
import com.app.learnquizjp.base.ConstantsPro.Companion.IS_FIRST_TIME_LAUNCH
import com.app.learnquizjp.base.ConstantsPro.Companion.PREF_NAME
import com.app.learnquizjp.base.ConstantsPro.Companion.PRIVATE_MODE


class PrefManager(mContext: Context) {
    var mPref: SharedPreferences
    var mEditor: SharedPreferences.Editor

    val isFirstTimeLaunch: Boolean
        get() = mPref.getBoolean(IS_FIRST_TIME_LAUNCH, true)

    init {
        mPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        mEditor = mPref.edit()
    }

    fun setFirstimeLaunch(isFirstTime: Boolean) {
        mEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        mEditor.apply()
    }

    companion object {
        private var mInstance: PrefManager? = null

        fun getInstance(context: Context): PrefManager {
            if (mInstance == null) {
                mInstance = PrefManager(context)
            }
            return mInstance as PrefManager
        }
    }
}