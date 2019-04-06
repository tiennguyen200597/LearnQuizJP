package com.app.learnquizjp.model

class Kotoba {

    var KOTOBA_ID : Int = 0
    var KOTOBA_HIRAGANA : String? = null
    var KOTOBA_KANJI : String? = null
    var KOTOBA_DESCRIPTION : String? = null
    var KOTOBA_EXAMPLE : String? = null

    constructor()

    constructor(hiragana : String, kanji : String, description : String,example : String){
        KOTOBA_HIRAGANA = hiragana
        KOTOBA_KANJI = kanji
        KOTOBA_DESCRIPTION = description
        KOTOBA_EXAMPLE = example
    }

    constructor(id : Int,hiragana : String, kanji : String, description : String,example : String){
        KOTOBA_ID = id
        KOTOBA_HIRAGANA = hiragana
        KOTOBA_KANJI = kanji
        KOTOBA_DESCRIPTION = description
        KOTOBA_EXAMPLE = example
    }

}