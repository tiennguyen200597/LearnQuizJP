package com.app.learnquizjp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.app.learnquizjp.Constants.Companion.CREATE_KOTOBA_TABLE
import com.app.learnquizjp.Constants.Companion.isDEBUG


class DBHelper(context: Context?) : SQLiteOpenHelper(context, "dictionary.sql", null, 1) {

    init {
        if (isDEBUG) Log.i("CREATE_KOTOBA_TABLE", CREATE_KOTOBA_TABLE)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_KOTOBA_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_KOTOBA_TABLE)
    }

}