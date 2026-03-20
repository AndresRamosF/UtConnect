package com.utcancun.utconnect.feed

data class Post(
    val id: Int,
    val userName: String,
    val time: String,
    val content: String,
    val likes: Int,
    val comments: Int,
    val commentList: List<Comment> = emptyList()
)

data class Comment(
    val id: Int,
    val user: String,
    val time: String,
    val text: String,
    val likes: Int
)
