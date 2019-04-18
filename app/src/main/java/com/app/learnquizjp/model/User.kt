package com.app.learnquizjp.model

class User {

    var id: String? = null
    var username: String? = null
    var profileImageUrl: String? = null
    //var favorite : MutableList<Kotoba> = mutableListOf()

    constructor(id: String, username: String, profileImageUrl: String) {
        this.id = id
        this.username = username
        this.profileImageUrl = profileImageUrl
    }

    /*constructor(id: String, username: String, favorite: MutableList<Kotoba>){
        this.id = id
        this.username = username
        this.favorite = favorite
    }*/

    constructor() {}

}
