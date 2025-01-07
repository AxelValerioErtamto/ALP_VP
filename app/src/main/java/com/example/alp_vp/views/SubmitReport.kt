package com.example.alp_vp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R
import com.example.alp_vp.ui.theme.ALP_VPTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubmitReportView() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.backgroundparking),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
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
                        fontSize = 40.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    ) {
                        Text(
                            "hub",
                            color = Color(0xffffa001),
                            fontSize = 40.sp,
                            fontWeight = FontWeight.W700
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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column() {
                Image(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = null,
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = "Report an Issue",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp),
                    )
                }
                // Title field
                Text("Title:", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                val title = remember { mutableStateOf("") }
                TextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF8F9FA)
                    )
                )

                // Description field
                Text("Description:", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                val description = remember { mutableStateOf("") }
                TextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF8F9FA)
                    )
                )

                // Upload Image field
                Text("Upload Image:", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                val imagePlaceholder = remember { mutableStateOf("") }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = imagePlaceholder.value,
                        onValueChange = { imagePlaceholder.value = it },
                        placeholder = { Text("Add file") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color(0xFFF8F9FA)
                        )
                    )
                    Button(
                        onClick = { /* Handle file upload */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffffa001)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Upload", color = Color.White)
                            Spacer(modifier = Modifier.width(2.dp))
                            Image(painter = painterResource(R.drawable.baseline_upload_24), contentDescription = null, colorFilter = ColorFilter.tint(Color.White))
                        }
                    }
                }

                Button(
                    onClick = { /* Handle submit report */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xffffa001)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Submit Report", color = Color.White)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(color = Color(0xffffa001))
                .padding(vertical = 16.dp), // Increased padding
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
fun SubmitReportPreview() {
    ALP_VPTheme {
        SubmitReportView()
    }
}