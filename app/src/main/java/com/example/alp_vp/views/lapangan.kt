package com.example.alp_vp.views

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
import androidx.compose.material3.Text
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
import com.example.alp_vp.ui.theme.ALP_VPTheme

@Composable
fun Lapangan() {
    val isFilled = arrayOf(
        true, false, false, true, true, true, false, true, false, true, false, false,
        true, true, true, false, false, true, false, true, true, false, true, false, true,
        false, true, true, false, true, false, true, true, false, false, true, true, true,
        true, false, false, true, true, true, false, true, false, true, false, false,
        true, true, true, false, false, true, false, true, true, false, true, false, true,
        false, true, true, false, true, false, true, true, false, false, true, true, true,
        false, true, false, true, false, false, true, true, true, false, false, true, false,
        true, true, false,
    )
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
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(Color.Gray),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Parkiran motor", color = Color.White, fontSize = 24.sp)
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    for (i in 0 until 15) {
                        VertiLapangan(isFilled[i])
                    }
                }
                Spacer(Modifier.height(32.dp))
                Row {
                    Column(
                        Modifier
                            .size(140.dp, 220.dp)
                            .background(Color.Gray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("Lapangan basket", color = Color.White, fontSize = 16.sp)
                    }
                    Spacer(Modifier.width(18.dp))
                    Column {
                        Row {
                            for (i in 15 until 23) {
                                VertiLapangan(isFilled[i])
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Row {
                            for (i in 23 until 31) {
                                VertiLapangan(isFilled[i])
                            }
                        }
                        Spacer(Modifier.height(40.dp))
                        Row {
                            for (i in 31 until 39) {
                                VertiLapangan(isFilled[i])
                            }
                        }
                        Spacer(Modifier.height(8.dp))
                        Row {
                            for (i in 39 until 47) {
                                VertiLapangan(isFilled[i])
                            }
                        }
                    }
                }
                Spacer(Modifier.height(32.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    for (i in 47 until 62) {
                        VertiLapangan(isFilled[i])
                    }
                }
                Spacer(Modifier.height(8.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    for (i in 62 until 77) {
                        VertiLapangan(isFilled[i])
                    }
                }
                Spacer(Modifier.height(32.dp))
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    for (i in 77 until 92) {
                        VertiLapangan(isFilled[i])
                    }
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

@Composable
fun VertiLapangan(isFilled: Boolean) {
    Spacer(Modifier.width(2.dp))
    Box(
        Modifier
            .background(
                if (isFilled) {
                    Color.Red
                } else {
                    Color.Green
                }
            )
            .size(20.dp, 40.dp)
    )
    Spacer(Modifier.width(2.dp))
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LapanganPreview() {
    ALP_VPTheme {
        Lapangan()
    }
}
