package com.utcancun.utconnect.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentsScreen(
    post: Post?,
    onBack: () -> Unit,
    onReplyClick: (Comment) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comentarios", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFF5F5F5)
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (post != null) {
                // Post Original
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(post.userName, fontWeight = FontWeight.ExtraBold, fontSize = 12.sp, color = Color(0xFF008060))
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(post.content, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                    }
                }

                HorizontalDivider(color = Color.LightGray, thickness = 1.dp)

                // Lista de comentarios
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(post.commentList) { item ->
                        CommentItem(comment = item, onReplyClick = { onReplyClick(item) })
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Post no encontrado")
                }
            }
        }
    }
}

@Composable
fun CommentItem(comment: Comment, onReplyClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(32.dp).clip(CircleShape).background(Color.LightGray))
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = comment.user, fontWeight = FontWeight.Bold, fontSize = 14.sp, modifier = Modifier.weight(1f))
            Text(text = comment.time, fontSize = 12.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = comment.text, fontSize = 14.sp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextButton(onClick = { /* Like */ }) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = null, modifier = Modifier.size(16.dp), tint = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                Text(comment.likes.toString(), color = Color.Gray, fontSize = 12.sp)
            }

            TextButton(onClick = onReplyClick) {
                Text("Responder", color = Color(0xFF0084FF), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }
        HorizontalDivider(color = Color(0xFFEEEEEE), thickness = 0.5.dp)
    }
}
