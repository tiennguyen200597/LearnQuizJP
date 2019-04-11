package com.app.learnquizjp.model

class User {

    var id: String? = null
    var username: String? = null
    var profileImageUrl: String? = null

    constructor(id: String, username: String, profileImageUrl: String) {
        this.id = id
        this.username = username
        this.profileImageUrl = profileImageUrl
    }

    constructor() {}

}
