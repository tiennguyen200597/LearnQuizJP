package com.app.learnquizjp.model

import java.io.Serializable

class Question : Serializable {
    var qzcode: Int = 0
    var qzcontent: String? = null
    var ascortect: String? = null
    var asincortecT1: String? = null
    var asincortecT2: String? = null
    var asincortecT3: String? = null
    var explain: String? = null
    var csfykindcode: Int = 0
    var csfylvcode: Int = 0

    constructor() {}

    constructor(
        QZCODE: Int,
        QZCONTENT: String,
        ASCORTECT: String,
        ASINCORTECT1: String,
        ASINCORTECT2: String,
        ASINCORTECT3: String,
        EXPLAIN: String,
        CSFYKINDCODE: Int,
        CSFYLVCODE: Int
    ) {
        this.qzcode = QZCODE
        this.qzcontent = QZCONTENT
        this.ascortect = ASCORTECT
        this.asincortecT1 = ASINCORTECT1
        this.asincortecT2 = ASINCORTECT2
        this.asincortecT3 = ASINCORTECT3
        this.explain = EXPLAIN
        this.csfykindcode = CSFYKINDCODE
        this.csfylvcode = CSFYLVCODE
    }
}
