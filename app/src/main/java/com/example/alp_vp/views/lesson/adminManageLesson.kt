package com.example.alp_vp.views.lesson

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp.R
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.models.LessonModel
import com.example.alp_vp.viewmodels.HomeViewModel
import com.example.alp_vp.viewmodels.lesson.LessonViewModel

@Composable
fun AdminManageLesson(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    token: String,
    context: Context,
    lessonViewModel: LessonViewModel = viewModel(factory = LessonViewModel.Factory)
) {
    val lessons by lessonViewModel.lessons
    val isLoading by lessonViewModel.isLoading
    val errorMessage by lessonViewModel.errorMessage

    LaunchedEffect(Unit) {
        lessonViewModel.fetchAllLessons(token)
    }

    Column(modifier = modifier.fillMaxSize()) {
        // TopAppBar
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "Park",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W600
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.White)
                            .padding(horizontal = 4.dp, vertical = 2.dp)
                    ) {
                        Text(
                            "hub",
                            color = Color(0xffffa001),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
                Button(
                    onClick = {
                        homeViewModel.logoutUser(token, navController)
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9534F)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "Logout",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }

        // Content Area
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.White)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color(0xffffa001)
                )
            } else if (errorMessage != null) {
                Text(
                    text = errorMessage.orEmpty(),
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(lessons) { lesson ->
                        LessonCard(
                            lesson = lesson,
                            token = token,
                            navController = navController,
                            lessonViewModel = lessonViewModel,
                            context = context
                        )
                    }
                }
            }
        }

        // Bottom Navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xffffa001))
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationItem(R.drawable.warning, "Report", iconSize = 32.dp, fontSize = 14.sp)
            NavigationItem(R.drawable.car, "Location", iconSize = 32.dp, fontSize = 14.sp)
            NavigationItem(R.drawable.book, "Lesson", iconSize = 32.dp, fontSize = 14.sp)
        }
    }
}

@Composable
fun LessonCard(
    lesson: LessonModel,
    token: String,
    navController: NavHostController,
    lessonViewModel: LessonViewModel,
    context: Context
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Black),
        elevation = CardDefaults.elevatedCardElevation(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
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
            Spacer(modifier = Modifier.height(16.dp))

            // Row for Update and Delete buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate("${PagesEnum.UpdateLesson.name}/${lesson.id}")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA001)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Update",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = {
                        lessonViewModel.deleteLesson(
                            token = token,
                            lessonId = lesson.id,
                            onSuccess = {
                                Toast.makeText(context, "Lesson deleted!", Toast.LENGTH_SHORT).show()
                            },
                            onError = { error ->
                                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD9534F)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Delete",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
