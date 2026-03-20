package com.utcancun.utconnect.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FeedScreen(
    posts: List<Post>,
    onCommentClick: (Post) -> Unit,
    onCreatePostClick: () -> Unit
) {
    Scaffold(
        topBar = { FeedTopBar() },
        bottomBar = { FeedBottomBar(onCreatePostClick = onCreatePostClick) },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(posts) { post ->
                PostItem(post = post, onCommentClick = { onCommentClick(post) })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedTopBar() {
    TopAppBar(
        title = { Text("UTConnect", fontWeight = FontWeight.Bold, color = Color(0xFF008060)) },
        navigationIcon = {
            IconButton(onClick = { /* Perfil */ }) {
                Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color(0xFF008060))
            }
        },
        actions = {
            IconButton(onClick = { /* Destacado */ }) {
                Icon(Icons.Default.Star, contentDescription = "Destacado", tint = Color(0xFF008060))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
    )
}

@Composable
fun PostItem(post: Post, onCommentClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray))
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = post.userName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Text(text = post.time, fontSize = 12.sp, color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = post.content, fontSize = 15.sp)
            Spacer(modifier = Modifier.height(12.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                TextButton(onClick = { /* Like */ }) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = "Like", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = post.likes.toString(), color = Color.Gray)
                }
                Spacer(modifier = Modifier.width(16.dp))
                TextButton(onClick = onCommentClick) {
                    Icon(Icons.Default.Email, contentDescription = "Comentar", tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = post.comments.toString(), color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun FeedBottomBar(onCreatePostClick: () -> Unit) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(icon = { Icon(Icons.Default.Home, "") }, label = { Text("Inicio") }, selected = true, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Search, "") }, label = { Text("Search") }, selected = false, onClick = {})
        NavigationBarItem(
            icon = { Icon(Icons.Default.AddCircle, "") },
            label = { Text("Post") },
            selected = false,
            onClick = onCreatePostClick
        )
        NavigationBarItem(icon = { Icon(Icons.Default.Notifications, "") }, label = { Text("Notif") }, selected = false, onClick = {})
        NavigationBarItem(icon = { Icon(Icons.Default.Email, "") }, label = { Text("Mensajes") }, selected = false, onClick = {})
    }
}
