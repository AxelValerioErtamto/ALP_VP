package com.example.alp_vp.views.lesson

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.alp_vp.R
import com.example.alp_vp.viewmodels.AdminUpdateLessonViewModel

@Composable
fun AdminUpdateLesson(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    token: String,
    context: Context,
    adminUpdateLessonViewModel: AdminUpdateLessonViewModel,
    lessonId: Int // Added lessonId parameter
) {
    // Fetch initial lesson details using the lessonId
    adminUpdateLessonViewModel.fetchLessonDetails(token, lessonId)

    // Observe the state from the ViewModel
    val title by adminUpdateLessonViewModel.title
    val description by adminUpdateLessonViewModel.description
    val content by adminUpdateLessonViewModel.content
    val updateError by adminUpdateLessonViewModel.updateError

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
                    text = "Update Lesson",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W600
                )
            }
        }

        // Content Area
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            // Lesson ID Display (Optional, to show current lesson being updated)
            Text("Lesson ID: $lessonId", fontSize = 16.sp, color = Color.Gray)

            // Title Input
            Text("Title:", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            OutlinedTextField(
                value = title, // Prepopulate with the existing title
                onValueChange = { adminUpdateLessonViewModel.updateTitle(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Enter title here") },
                shape = RoundedCornerShape(8.dp)
            )

            // Description Input
            Text("Description:", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            OutlinedTextField(
                value = description, // Prepopulate with the existing description
                onValueChange = { adminUpdateLessonViewModel.updateDescription(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Enter description here") },
                shape = RoundedCornerShape(8.dp)
            )

            // Content Input
            Text("Content:", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            OutlinedTextField(
                value = content, // Prepopulate with the existing content
                onValueChange = { adminUpdateLessonViewModel.updateContent(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Enter content here") },
                shape = RoundedCornerShape(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Display Error Message
            updateError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Update Button
            Button(
                onClick = {
                    adminUpdateLessonViewModel.updateLesson(token)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA726),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                enabled = !adminUpdateLessonViewModel.isLoading.value // Disable the button when loading
            ) {
                Text(text = "Update", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}
