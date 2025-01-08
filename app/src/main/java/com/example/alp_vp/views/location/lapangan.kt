package com.example.alp_vp.views.location

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
import com.example.alp_vp.views.lesson.NavigationItem

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
                    .padding(16.dp)
                    .fillMaxSize()) {
                Row {
                    Spacer(Modifier.width(60.dp))
                    for (i in 0 until 14) {
                        VertiLapangan(isFilled[i])
                    }
                }
                Spacer(Modifier.height(18.dp))
                Row {
                    Column {
                        for (i in 0 until 40) {
                            HorizLapangan(isFilled[i])
                        }
                    }
                    Spacer(Modifier.width(36.dp))
                    Column {
                        for (i in 0 until 40) {
                            HorizLapangan(isFilled[i])
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    Column {
                        for (i in 0 until 40) {
                            HorizLapangan(isFilled[i])
                        }
                    }
                    Spacer(Modifier.width(36.dp))
                    Column {
                        Row(Modifier.width(148.dp), horizontalArrangement = Arrangement.Center) {
                            for (i in 0 until 7) {
                                VertiLapangan(isFilled[i])
                            }
                        }
                        Spacer(Modifier.height(2.dp))
                        Column(
                            Modifier
                                .background(Color.Gray)
                                .size(148.dp, 180.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("Lapangan basket")
                        }
                        Spacer(Modifier.height(6.dp))
                        Row {
                            Column {
                                for (i in 0 until 25) {
                                    HorizLapangan(isFilled[i])
                                }
                            }
                            Spacer(Modifier.width(8.dp))
                            Column {
                                for (i in 0 until 25) {
                                    HorizLapangan(isFilled[i])
                                }
                            }
                            Spacer(Modifier.width(36.dp))
                            Column {
                                for (i in 0 until 25) {
                                    HorizLapangan(isFilled[i])
                                }
                            }
                            Spacer(Modifier.width(8.dp))
                            Column {
                                for (i in 0 until 25) {
                                    HorizLapangan(isFilled[i])
                                }
                            }
                        }
                    }
                    Spacer(Modifier.width(36.dp))
                    Column {
                        for (i in 0 until 40) {
                            HorizLapangan(isFilled[i])
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
            .size(12.dp, 24.dp)
    )
    Spacer(Modifier.width(2.dp))
}

@Composable
fun HorizLapangan(isFilled: Boolean) {
    Spacer(Modifier.height(1.dp))
    Box(
        Modifier
            .background(
                if (isFilled) {
                    Color.Red
                } else {
                    Color.Green
                }
            )
            .size(24.dp, 12.dp)
    )
    Spacer(Modifier.height(1.dp))
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LapanganPreview() {
    ALP_VPTheme {
        Lapangan()
    }
}
