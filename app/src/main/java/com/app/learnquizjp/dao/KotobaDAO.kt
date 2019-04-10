package com.app.learnquizjp.dao

import com.app.learnquizjp.base.Constants
import android.content.ContentValues
import android.content.Context
import com.app.learnquizjp.database.DBHelper
import com.app.learnquizjp.model.Kotoba


class KotobaDAO : Constants(){

    private var dbHelper: DBHelper? = null

    fun UserDAO(context: Context) {
        this.dbHelper = DBHelper(context)
    }

    fun UserDAO(dbHelper: DBHelper){
        this.dbHelper = dbHelper
    }

    fun insertKotoba(kotoba : Kotoba): Long {

        var result: Long = -1

        val cv = ContentValues()
        cv.put(KOTOBA_HIRAGANA, KOTOBA_HIRAGANA)
        cv.put(KOTOBA_KANJI, KOTOBA_KANJI)
        cv.put(KOTOBA_DESCRIPTION, KOTOBA_DESCRIPTION)
        cv.put(KOTOBA_EXAMPLE, KOTOBA_EXAMPLE)

        val sqLiteDatabase = dbHelper!!.writableDatabase
        result = sqLiteDatabase.insert(KOTOBA_TABLE, null, cv)
        sqLiteDatabase.close()

        return result

    }

    fun updateKotoba(kotoba : Kotoba): Long {

        var result: Long = -1
        val cv = ContentValues()
        cv.put(KOTOBA_HIRAGANA, KOTOBA_HIRAGANA)
        cv.put(KOTOBA_KANJI, KOTOBA_KANJI)
        cv.put(KOTOBA_DESCRIPTION, KOTOBA_DESCRIPTION)
        cv.put(KOTOBA_EXAMPLE, KOTOBA_EXAMPLE)

        val sqLiteDatabase = dbHelper!!.writableDatabase
        result = sqLiteDatabase.update(KOTOBA_TABLE, cv, "$KOTOBA_ID= ?", arrayOf(KOTOBA_ID)).toLong()
        sqLiteDatabase.close()

        return result

    }

    fun deleteKotoba(id : Int): Long {

        var result: Long = -1

        val sqLiteDatabase = dbHelper!!.writableDatabase
        result = sqLiteDatabase.delete(KOTOBA_TABLE, "$KOTOBA_ID = ?", arrayOf(id.toString())).toLong()
        sqLiteDatabase.close()

        return result

    }

    fun getAllKotoba(): List<Kotoba> {

        val kotobas : MutableList<Kotoba> = mutableListOf()

        val QUERY = "SELECT * FROM $KOTOBA_TABLE"
        val sqLiteDatabase = dbHelper!!.writableDatabase
        val cursor = sqLiteDatabase.rawQuery(QUERY, null)

        if (cursor != null) {

            if (cursor.count > 0) {

                cursor.moveToFirst()
                while (!cursor.isAfterLast) {

                    val hiragana = cursor.getString(cursor.getColumnIndex(KOTOBA_HIRAGANA))
                    val kanji = cursor.getString(cursor.getColumnIndex(KOTOBA_KANJI))
                    val description = cursor.getString(cursor.getColumnIndex(KOTOBA_DESCRIPTION))
                    val example = cursor.getString(cursor.getColumnIndex(KOTOBA_EXAMPLE))

                    val kotoba = Kotoba(hiragana, kanji, description, example)

                    kotobas.add(kotoba)
                    cursor.moveToNext()
                }
                cursor.close()
                sqLiteDatabase.close()
            }
        }
        return kotobas
    }

    fun getSearchKotoba(search_key : String): Kotoba? {

        var kotoba : Kotoba? = null
        val db = dbHelper!!.readableDatabase
        val cursor = db.query(
            KOTOBA_TABLE,
            arrayOf(KOTOBA_HIRAGANA, KOTOBA_KANJI, KOTOBA_DESCRIPTION, KOTOBA_EXAMPLE),
            "$KOTOBA_HIRAGANA = ? ",
            arrayOf(search_key),
            null,
            null,
            null,
            null
        )

        // moveToFirst : kiem tra xem cursor co chua du lieu khong, ham nay tra ve gia tri la true or false
        if (cursor != null && cursor.moveToFirst()) {

            val hiragana = cursor.getString(cursor.getColumnIndex(KOTOBA_HIRAGANA))

            val kanji = cursor.getString(cursor.getColumnIndex(KOTOBA_KANJI))

            val description = cursor.getString(cursor.getColumnIndex(KOTOBA_DESCRIPTION))

            val example = cursor.getString(cursor.getColumnIndex(KOTOBA_EXAMPLE))

            // khoi tao user voi cac gia tri lay duoc
            kotoba = Kotoba(hiragana, kanji, description, example)
        }
        cursor!!.close()
        return kotoba
    }

}