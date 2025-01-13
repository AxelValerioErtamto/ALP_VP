package com.example.alp_vp.views.lesson

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.models.LessonModel
import com.example.alp_vp.viewmodels.lesson.LessonViewModel

@Composable
fun LessonPage(
    lessonViewModel: LessonViewModel,
    token: String,
    onLessonClick: (LessonModel) -> Unit
) {

    LaunchedEffect(Unit) {
        lessonViewModel.fetchAllLessons(token)
    }

    val lessons by lessonViewModel.lessons
    val isLoading by lessonViewModel.isLoading
    val errorMessage by lessonViewModel.errorMessage

    Column(modifier = Modifier.fillMaxSize()) {
        // Top Bar
        Column(
            modifier = Modifier
                .background(color = Color(0xffffa001))
                .fillMaxWidth()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Available Lessons",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }

        // Content Area
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xffffa001))
            }
        } else if (errorMessage != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(lessons) { lesson ->
                    LessonCard(lesson = lesson, onLessonClick = onLessonClick)
                }
            }
        }
    }
}

@Composable
fun LessonCard(lesson: LessonModel, onLessonClick: (LessonModel) -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp) // Removed clickable modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = lesson.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = lesson.description,
                fontSize = 16.sp,
                color = Color.Gray
            )

            // Learn Button
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onLessonClick(lesson) }, // Button click triggers onLessonClick
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffffa001)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Learn",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



@Composable
fun NavigationItem(imageRes: Int, label: String, iconSize: Dp = 24.dp, fontSize: TextUnit = 12.sp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = label,
            modifier = Modifier.size(iconSize)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = label,
            color = Color.White,
            fontSize = fontSize
        )
    }
}
