package com.example.alp_vp.views.location

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.alp_vp.R
import com.example.alp_vp.repositories.MockLocationRepository
import com.example.alp_vp.repositories.MockUserRepository
import com.example.alp_vp.uistates.LocationUIState
import com.example.alp_vp.viewmodels.LapanganViewModel
import com.example.alp_vp.views.lesson.NavigationItem

@Composable
fun Lapangan(viewModel: LapanganViewModel) {
    val locationUIState = viewModel.locationUIState

    LaunchedEffect(Unit) {
        viewModel.getAllLapangans()
    }

    if (locationUIState is LocationUIState.Loading) {
        CircularProgressIndicator()
    }

    if (locationUIState is LocationUIState.Failed) {
        Text("Error: ${locationUIState.errorMessage}")
    }

    if (locationUIState is LocationUIState.Success) {
        val colors = viewModel.getLapanganColors()

        Column(modifier = Modifier.fillMaxSize()) {
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
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier
                        .padding(8.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Row {
                        Spacer(Modifier.width(36.dp))
                        for (i in 260 until 274) {
                            VertiLapangan(colors[i])
                        }
                        Spacer(Modifier.width(4.dp))
                        Box(Modifier.background(Color.Gray).size(24.dp, 24.dp), contentAlignment = Alignment.Center){
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_up_24), contentDescription = null)
                        }
                        Spacer(Modifier.width(3.dp))
                        Box(Modifier.background(Color.Gray).size(24.dp, 24.dp), contentAlignment = Alignment.Center){
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_down_24), contentDescription = null)
                        }
                    }
                    Row(Modifier.background(Color.Gray).size(264.dp, 24.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                        Image(painterResource(R.drawable.baseline_keyboard_double_arrow_right_24), contentDescription = null)
                        Image(painterResource(R.drawable.baseline_keyboard_double_arrow_right_24), contentDescription = null)
                        Spacer(Modifier.height(0.dp))
                    }
                    Row {
                        Column {
                            for (i in 259 downTo  220) {
                                HorizLapangan(colors[i])
                            }
                        }
                        Spacer(Modifier.width(8.dp))
                        Box(Modifier.background(Color.Gray).size(24.dp, 492.dp), contentAlignment = Alignment.Center){
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_up_24), contentDescription = null)
                        }
                        Spacer(Modifier.width(8.dp))
                        Column {
                            for (i in 219 downTo 180) {
                                HorizLapangan(colors[i])
                            }
                        }
                        Spacer(Modifier.width(8.dp))
                        Column {
                            for (i in 179 downTo 140) {
                                HorizLapangan(colors[i])
                            }
                        }
                        Spacer(Modifier.width(8.dp))
                        Box(Modifier.background(Color.Gray).size(24.dp, 492.dp), contentAlignment = Alignment.Center){
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_up_24), contentDescription = null)
                        }
                        Spacer(Modifier.width(8.dp))
                        Column {
                            Spacer(Modifier.height(4.dp))
                            Row(Modifier.width(120.dp), horizontalArrangement = Arrangement.Center) {
                                for (i in 274 until 281) {
                                    VertiLapangan(colors[i])
                                }
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(
                                Modifier
                                    .background(Color(0xff4c6444))
                                    .size(120.dp, 152.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Lapangan basket", color = Color.White, fontSize = 14.sp)
                            }
                            Spacer(Modifier.height(4.dp))
                            Row {
                                Column {
                                    for (i in 139 downTo 115) {
                                        HorizLapangan(colors[i])
                                    }
                                }
                                Spacer(Modifier.width(8.dp))
                                Column {
                                    for (i in 114 downTo 90) {
                                        HorizLapangan(colors[i])
                                    }
                                }
                                Spacer(Modifier.width(8.dp))
                                Box(Modifier.background(Color.Gray).size(24.dp, 312.dp), contentAlignment = Alignment.Center){
                                    Image(painterResource(R.drawable.baseline_keyboard_double_arrow_up_24), contentDescription = null)
                                }
                                Spacer(Modifier.width(8.dp))
                                Column {
                                    for (i in 89 downTo 65) {
                                        HorizLapangan(colors[i])
                                    }
                                }
                                Spacer(Modifier.width(8.dp))
                                Column {
                                    for (i in 40 until 65) {
                                        HorizLapangan(colors[i])
                                    }
                                }
                            }
                        }
                        Spacer(Modifier.width(8.dp))
                        Box(Modifier.background(Color.Gray).size(24.dp, 492.dp), contentAlignment = Alignment.Center){
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_down_24), contentDescription = null)
                        }
                        Spacer(Modifier.width(8.dp))
                        Column {
                            for (i in 0 until 40) {
                                HorizLapangan(colors[i])
                            }
                        }
                    }
                    Row(Modifier.background(Color.Gray).size(264.dp, 24.dp), horizontalArrangement = Arrangement.SpaceEvenly){
                        Image(painterResource(R.drawable.baseline_keyboard_double_arrow_left_24), contentDescription = null)
                        Image(painterResource(R.drawable.baseline_keyboard_double_arrow_left_24), contentDescription = null)
                        Image(painterResource(R.drawable.baseline_keyboard_double_arrow_left_24), contentDescription = null)
                    }
                }
            }

            // Bottom Navigation
            Row(
                modifier = Modifier
                    .fillMaxWidth()
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
}

@Composable
fun VertiLapangan(color: Color) {
    Spacer(Modifier.width(2.dp))
    Box(
        Modifier
            .background(color)
            .size(8.dp, 16.dp)
    )
    Spacer(Modifier.width(2.dp))
}

@Composable
fun HorizLapangan(color: Color) {
    Spacer(Modifier.height(4.dp))
    Box(
        Modifier
            .background(color)
            .size(16.dp, 8.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LapanganPreview() {
    val viewModel = LapanganViewModel(MockLocationRepository(), MockUserRepository())
    viewModel.getAllLapangans()
    Lapangan(viewModel = viewModel)
}
