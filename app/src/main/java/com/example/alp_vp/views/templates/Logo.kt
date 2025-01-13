package com.example.alp_vp.views.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogoBig() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Park", color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.W600)
        Spacer(Modifier.width(2.dp))
        Box(
            Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color.White)
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Text(
                "hub",
                color = Color(0xffffa001),
                fontSize = 36.sp,
                fontWeight = FontWeight.W600
            )
        }
    }
}

@Composable
fun LogoSmall() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
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
    }
}