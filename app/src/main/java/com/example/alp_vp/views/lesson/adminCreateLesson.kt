package com.example.alp_vp.views.lesson

import android.content.Context
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alp_vp.viewmodels.AdminCreateLessonViewModel
import com.example.alp_vp.repositories.LessonRepository
@Composable
fun AdminCreateLesson(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    token: String,
    context: Context,
    adminCreateLessonViewModel: AdminCreateLessonViewModel // Correctly using this parameter
) {
    // Observe the state from the ViewModel
    val title by adminCreateLessonViewModel.title
    val description by adminCreateLessonViewModel.description
    val content by adminCreateLessonViewModel.content
    val imageUri by adminCreateLessonViewModel.imageUri
    val isLoading by adminCreateLessonViewModel.isLoading
    val creationError by adminCreateLessonViewModel.creationError


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
                    text = "Create Lesson",
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
            // Title Input
            Text("Title:", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            OutlinedTextField(
                value = title,
                onValueChange = { adminCreateLessonViewModel.updateTitle(it) }, // Use viewModel here
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Enter title here") },
                shape = RoundedCornerShape(8.dp)
            )

            // Description Input
            Text("Description:", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            OutlinedTextField(
                value = description,
                onValueChange = { adminCreateLessonViewModel.updateDescription(it) }, // Use viewModel here
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Enter description here") },
                shape = RoundedCornerShape(8.dp)
            )

            // Content Input
            Text("Content:", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            OutlinedTextField(
                value = content,
                onValueChange = { adminCreateLessonViewModel.updateContent(it) }, // Use viewModel here
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(vertical = 8.dp),
                placeholder = { Text(text = "Enter content here") },
                shape = RoundedCornerShape(8.dp)
            )


            Spacer(modifier = Modifier.height(24.dp))

            // Display Error Message
            creationError?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Create Button
            Button(
                onClick = { adminCreateLessonViewModel.createLesson(token) }, // Use viewModel here
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFA726),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                } else {
                    Text(text = "Create", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
