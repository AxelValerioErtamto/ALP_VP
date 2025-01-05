package com.example.alp_vp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
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

@Composable
fun HomePage() {
    Column(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .background(color = Color(0xffffa001))
                .fillMaxWidth()
                .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            //TopAppBar
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
        //Content
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFD59E), shape = RoundedCornerShape(16.dp))
                        .padding(24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_person),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Welcome to Park hub's Dashboard, John Doe!",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(id = R.drawable.notifications),
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }

        }

        //Bottom Navigation

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
fun HomePagePreview() {
    HomePage()
}
