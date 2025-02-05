package com.example.alp_vp.views.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alp_vp.R
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.views.templates.BotAppBar
import com.example.alp_vp.viewmodels.HomeViewModel

@Composable
fun AdminPage(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
    navController: NavHostController,
    token: String,
    context: Context
) {
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
                            text = "You are logged in as\nAdmin.",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = { navController.navigate(PagesEnum.CreateLesson.name)},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA726),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Create Lesson", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.navigate(PagesEnum.ManageLesson.name) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFA726),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "Manage Lessons", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        BotAppBar(navController)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AdminPagePreview() {
    AdminPage(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        homeViewModel = viewModel(factory = HomeViewModel.Factory),
        navController = rememberNavController(),
        token = "",
        context = LocalContext.current
    )
}
