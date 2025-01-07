package com.example.alp_vp.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R

@Composable
fun AdminManageLesson() {
    Column(modifier = Modifier.fillMaxSize()) {
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
                    onClick = { /* Handle logout */ },
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
                .background(Color.White),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Title with Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Available Lessons:",
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Lesson Card
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
                            text = "Cara Parkir Paralel",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Lesson ini berisi materi lengkap mengenai cara parkir paralel.",
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
                                onClick = { /* Handle Update button click */ },
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
                                onClick = { /* Handle Delete button click */ },
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminManageLessonPreview() {
    AdminManageLesson()
}
