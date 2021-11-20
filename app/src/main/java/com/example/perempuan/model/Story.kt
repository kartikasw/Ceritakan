package com.example.perempuan.model

data class Story(
    val user_uid : String = "",
    val status: String = "",
    val title: String,
    val content: String,
    var likeCount: Int = 0
    )
