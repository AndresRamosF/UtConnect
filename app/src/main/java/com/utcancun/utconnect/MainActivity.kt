package com.utcancun.utconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.utcancun.utconnect.feed.*

class MainActivity : ComponentActivity() {
    // Usamos el ViewModel para que los datos persistan
    private val viewModel: FeedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation(viewModel)
        }
    }
}

@Composable
fun AppNavigation(viewModel: FeedViewModel) {
    val navController = rememberNavController()
    // Estado simple para saber a qué post le estamos comentando
    var selectedPostId by remember { mutableStateOf<Int?>(null) }

    NavHost(navController = navController, startDestination = "feed") {
        composable("feed") {
            FeedScreen(
                posts = viewModel.posts,
                onCommentClick = { post ->
                    selectedPostId = post.id
                    navController.navigate("comments")
                },
                onCreatePostClick = { navController.navigate("create_post") }
            )
        }
        
        composable("comments") {
            val post = viewModel.posts.find { it.id == selectedPostId }
            CommentsScreen(
                post = post,
                onBack = { navController.popBackStack() },
                onReplyClick = { comment ->
                    // Por ahora navegamos a responder el post general
                    navController.navigate("reply")
                }
            )
        }
        
        composable("create_post") {
            CreatePostScreen(
                onBack = { navController.popBackStack() },
                onPostCreated = { text ->
                    viewModel.addPost(text)
                }
            )
        }
        
        composable("reply") {
            ReplyScreen(
                onBack = { navController.popBackStack() },
                onReplySent = { text ->
                    selectedPostId?.let { id ->
                        viewModel.addComment(id, text)
                    }
                }
            )
        }
    }
}
