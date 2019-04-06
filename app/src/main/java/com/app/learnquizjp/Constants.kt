package com.app.learnquizjp

open class Constants {

    companion object {

        val isDEBUG = true

        //User Table
        val KOTOBA_TABLE = "kotoba"
        //Column
        val KOTOBA_ID = "id"
        val KOTOBA_HIRAGANA = "hiragana"
        val KOTOBA_KANJI = "kanji"
        val KOTOBA_DESCRIPTION = "description"
        val KOTOBA_EXAMPLE = "example"
        //Query create kotoba(id int primary key auto increment,hiragana nvarchar(50) not null, kanji nvarchar(50), description nvarchar(50) not null,example nvarchar(255))
        val CREATE_KOTOBA_TABLE = "CREATE TABLE " + KOTOBA_TABLE + "(" +
                "" + KOTOBA_ID + " INT PRIMARY KEY AUTO INCREMENT," +
                "" + KOTOBA_HIRAGANA + " NVARCHAR(50) NOT NULL," +
                "" + KOTOBA_KANJI + " NVARCHAR(50)," +
                "" + KOTOBA_DESCRIPTION + " NVARCHAR(50) NOT NULL" +
                "" + KOTOBA_EXAMPLE + " NVARCHAR(255)" +
                ")"

    }

}