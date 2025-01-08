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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.alp_vp.views.lesson.NavigationItem

@Composable
fun Gedung(lantai: Int) {
    val isFilled = arrayOf(
        true, false, false, true, true, true, false, true, false, false, true, false,
        false, true, false, false, true, false, false, false, true, true, false, false,
        false, true, false, false, true, true, true, false, false, true, false, true,
        true, true, true
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
            Row(
                Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (lantai%2 == 0) {
                    Column(horizontalAlignment = Alignment.End) {
                        Column(Modifier.background(Color.Gray).size(64.dp, 40.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_right_24), contentDescription = null)
                        }
                        for (i in 0 until 18) {
                            HorizGedung(isFilled[i])
                        }
                        Column(Modifier.background(Color.Gray).size(64.dp, 40.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_left_24), contentDescription = null)
                        }
                    }
                } else {
                    Column {
                        for (i in 0 until 21) {
                            HorizGedung(isFilled[i])
                        }
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(R.drawable.baseline_arrow_drop_up_24), contentDescription = null, Modifier.size(128.dp))
                    Text("P$lantai", fontSize = 80.sp, fontWeight = FontWeight.W500)
                    Image(painterResource(R.drawable.baseline_arrow_drop_down_24), contentDescription = null, Modifier.size(128.dp))
                }
                if (lantai%2 == 0) {
                    Column {
                        for (i in 18 until 32) {
                            HorizGedung(isFilled[i])
                        }
                        Column(Modifier.size(40.dp, 84.dp).background(Color.Cyan), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painterResource(R.drawable.baseline_transfer_within_a_station_24), contentDescription = null, Modifier.size(32.dp))
                        }
                        for (i in 32 until 36) {
                            HorizGedung(isFilled[i])
                        }
                    }
                } else {
                    Column {
                        Column(Modifier.background(Color.Gray).size(64.dp, 40.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_right_24), contentDescription = null)
                        }
                        for (i in 21 until 39) {
                            HorizGedung(isFilled[i])
                        }
                        Column(Modifier.background(Color.Gray).size(64.dp, 40.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Image(painterResource(R.drawable.baseline_keyboard_double_arrow_left_24), contentDescription = null)
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
fun HorizGedung(isFilled: Boolean) {
    Spacer(Modifier.height(4.dp))
    Box(
        Modifier
            .background(
                if (isFilled) {
                    Color.Red
                } else {
                    Color.Green
                }
            )
            .size(40.dp, 20.dp)
    )
    Spacer(Modifier.height(4.dp))
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GedungPreview() {
    Gedung(6)
}
