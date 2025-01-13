package com.example.alp_vp.views.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alp_vp.R
import com.example.alp_vp.enums.PagesEnum
import com.example.alp_vp.views.lesson.NavigationItem

@Composable
fun BotAppBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xffffa001))
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.clickable {
                navController.navigate(PagesEnum.CreateReport.name) // Navigate to SubmitReport page
            }
        ) {
            NavigationItem(
                R.drawable.warning,
                label = "Report",
                iconSize = 32.dp,
                fontSize = 14.sp
            )
        }
        Box(
            modifier = Modifier.clickable {
                navController.navigate(PagesEnum.Locations.name)
            }
        ) {
            NavigationItem(R.drawable.car, "Location", iconSize = 32.dp, fontSize = 14.sp)
        }
        NavigationItem(R.drawable.book, "Lesson", iconSize = 32.dp, fontSize = 14.sp)
    }
}