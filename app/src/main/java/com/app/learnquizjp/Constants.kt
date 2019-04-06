package com.app.learnquizjp

open class Constants {

    companion object {

        const val isDEBUG = true

        //User Table
        const val KOTOBA_TABLE = "kotoba"
        //Column
        const val KOTOBA_ID = "id"
        const val KOTOBA_HIRAGANA = "hiragana"
        const val KOTOBA_KANJI = "kanji"
        const val KOTOBA_DESCRIPTION = "description"
        const val KOTOBA_EXAMPLE = "example"
        //Query create kotoba(id int primary key auto increment,hiragana nvarchar(50) not null, kanji nvarchar(50), description nvarchar(50) not null,example nvarchar(255))
        const val CREATE_KOTOBA_TABLE = "CREATE TABLE " + KOTOBA_TABLE + "(" +
                "" + KOTOBA_ID + " INT PRIMARY KEY AUTO INCREMENT," +
                "" + KOTOBA_HIRAGANA + " NVARCHAR(50) NOT NULL," +
                "" + KOTOBA_KANJI + " NVARCHAR(50)," +
                "" + KOTOBA_DESCRIPTION + " NVARCHAR(50) NOT NULL" +
                "" + KOTOBA_EXAMPLE + " NVARCHAR(255)" +
                ")"

    }

}