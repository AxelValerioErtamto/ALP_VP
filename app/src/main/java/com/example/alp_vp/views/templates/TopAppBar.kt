package com.example.alp_vp.views.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TopAppBar(navController: NavController) {
    Column(
        modifier = Modifier
            .background(color = Color(0xffffa001))
            .fillMaxWidth()
            .padding(top = 40.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
    ) {
        LogoSmall(navController)
    }
}