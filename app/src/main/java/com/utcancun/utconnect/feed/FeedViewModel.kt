package com.utcancun.utconnect.feed

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FeedViewModel : ViewModel() {
    private val _posts = mutableStateListOf(
        Post(
            id = 1,
            userName = "KRISTOPHER EIAEL CORTEZ VARGAS",
            time = "7h",
            content = "TORNEO DE BASKETBALL",
            likes = 2434,
            comments = 1,
            commentList = listOf(
                Comment(1, "SANTIAGO RAMOS", "2h", "¡Excelente iniciativa! Yo me apunto para el torneo.", 12)
            )
        ),
        Post(
            id = 2,
            userName = "JESUS ADRIAN CARDENAS CALDERON",
            time = "8h",
            content = "El día de mañana habrá torneo de ajedrez organizado por los alumnos del club de ajedrez",
            likes = 2434,
            comments = 1,
            commentList = listOf(
                Comment(2, "MARIA FERNANDA", "1h", "¿Saben si habrá premios para los primeros lugares?", 5)
            )
        )
    )
    val posts: List<Post> get() = _posts

    fun addPost(content: String) {
        val newPost = Post(
            id = _posts.size + 1,
            userName = "TU PERFIL (ALEX)",
            time = "Ahora",
            content = content,
            likes = 0,
            comments = 0,
            commentList = emptyList()
        )
        _posts.add(0, newPost)
    }

    fun addComment(postId: Int, text: String) {
        val index = _posts.indexOfFirst { it.id == postId }
        if (index != -1) {
            val post = _posts[index]
            val newComment = Comment(
                id = post.commentList.size + 1,
                user = "TU PERFIL (ALEX)",
                time = "Ahora",
                text = text,
                likes = 0
            )
            val updatedPost = post.copy(
                commentList = post.commentList + newComment,
                comments = post.comments + 1
            )
            _posts[index] = updatedPost
        }
    }
}
