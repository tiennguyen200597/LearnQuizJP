package com.app.learnquizjp.model

class User {

    var id: String? = null
    var username: String? = null
    var profileImageUrl: String = "default"
    var favorite : MutableList<Int>? = ArrayList()

    constructor(id: String, username: String) {
        this.id = id
        this.username = username
        //this.profileImageUrl = profileImageUrl
        //this.favorite = favorite
    }

    constructor() {}

}
